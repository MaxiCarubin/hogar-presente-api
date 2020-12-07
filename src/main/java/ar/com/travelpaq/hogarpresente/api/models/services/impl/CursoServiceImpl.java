package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.*;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.*;
import ar.com.travelpaq.hogarpresente.api.models.repository.IContenidoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUnidadRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUsuarioRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import ar.com.travelpaq.hogarpresente.api.security.enums.RoleNombre;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Autowired
    private UnidadMapper unidadMapper;

    @Autowired
    private IUnidadRepository unidadRepository;

    @Autowired
    private ContenidoMapper contenidoMapper;

    @Autowired
    private IContenidoRepository contenidoRepository;

    private static RoleEntity roleAdmin = new RoleEntity(3, RoleNombre.ROLE_ADMIN);

    private static RoleEntity roleCapacitador = new RoleEntity(2, RoleNombre.ROLE_CAPACITADOR);

    private static RoleEntity roleAlumno = new RoleEntity(1, RoleNombre.ROLE_ALUMNO);

    @Override
    public ResponseEntity<?> findAll() {
        List<CursoEntity> cursoEntities = cursoRepository.findAll();
        List<CompletoCursoDto> cursoDtos = new ArrayList<>();
        for (CursoEntity cursoEntity : cursoEntities){
            CompletoCursoDto cursoDto = cursoMapper.mapCursoDtoToCursoEntity(cursoEntity);
            cursoDtos.add(cursoDto);
        }
        return new ResponseEntity(cursoDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el curso en la base de datos"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.findById(id).get();
        CompletoCursoDto cursoDto = cursoMapper.mapCursoDtoToCursoEntity(cursoEntity);
        return new ResponseEntity(cursoDto, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findById2(Long id) {
        if(cursoRepository.findById(id) != null){
            return true;
        }
        else return false;
    }

    @Override
    public ResponseEntity<?> create(CursoDto newCurso) {
        if(StringUtils.isBlank(newCurso.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newCurso.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if(newCurso.getPrecio() < 0)
            return new ResponseEntity(new Mensaje("El precio no puede ser negativo"), HttpStatus.BAD_REQUEST);
        if(!usuarioRepository.existsById(newCurso.getUsuarioId()))
            return new ResponseEntity(new Mensaje("El usuario no esta en la base de datos"), HttpStatus.BAD_REQUEST);
        UsuarioEntity usuarioEntity = usuarioRepository.findById(newCurso.getUsuarioId()).get();
        if(usuarioEntity.getRoles().contains(roleAlumno))
            return new ResponseEntity(new Mensaje("El usuario debe ser un capacitador o admin para crear cursos"), HttpStatus.BAD_REQUEST);
        CursoEntity cursoEntity = cursoMapper.mapCursoToCursoEntity(newCurso);
        cursoEntity.setHabilitado(false);
        cursoEntity.setUsuario(usuarioEntity);
        cursoRepository.save(cursoEntity);
        usuarioEntity.getCursos().add(cursoEntity);
        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Curso creado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(CursoDto cursoDto, Long id) {
        if (!cursoRepository.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el curso en la base de datos"), HttpStatus.NOT_FOUND);
        }
        else {
            if (StringUtils.isBlank(cursoDto.getNombre()))
                return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
            if (StringUtils.isBlank(cursoDto.getDescripcion()))
                return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
            if (cursoDto.getPrecio() < 0)
                return new ResponseEntity(new Mensaje("El precio no puede ser negativo"), HttpStatus.BAD_REQUEST);
            if(!usuarioRepository.existsById(cursoDto.getUsuarioId()))
                return new ResponseEntity(new Mensaje("El usuario no esta en la base de datos"), HttpStatus.BAD_REQUEST);
            UsuarioEntity usuarioNuevo = usuarioRepository.findById(cursoDto.getUsuarioId()).get();
            if(usuarioNuevo.getRoles().contains(roleAlumno))
                return new ResponseEntity(new Mensaje("El usuario debe ser un capacitador o admin para editar el curso"), HttpStatus.BAD_REQUEST);
            CursoEntity cursoEntity = cursoRepository.getOne(id);
            UsuarioEntity usuarioViejo = cursoRepository.findById(id).get().getUsuario();
            if (usuarioNuevo.getId() != usuarioViejo.getId() && usuarioNuevo.getRoles().contains(roleCapacitador))
                return new ResponseEntity
                        (
                                new Mensaje
                                        (
                                                "El ID del creador del curso que se quiere modificar debe coincidir con el ID del usuario que esta modificando el curso o ese usuario debe ser ADMIN."
                                        )
                                , HttpStatus.BAD_REQUEST
                        );

            cursoEntity.setNombre(cursoDto.getNombre());
            cursoEntity.setDescripcion(cursoDto.getDescripcion());
            cursoEntity.setPrecio(cursoDto.getPrecio());
            cursoEntity.setImagen(cursoDto.getImagen());
            cursoEntity.setCategoria(cursoDto.getCategoria());
            cursoEntity.setHabilitado(cursoDto.isHabilitado());
            cursoRepository.save(cursoEntity);
            return new ResponseEntity(new Mensaje("Curso Actualizado!"), HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        cursoRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Curso Eliminado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> habilitarCurso(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        cursoEntity.setHabilitado(true);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso Habilitado!"), HttpStatus.OK);
    }
}
