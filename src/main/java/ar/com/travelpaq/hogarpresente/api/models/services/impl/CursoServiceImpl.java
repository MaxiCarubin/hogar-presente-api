package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.cloudinary.repository.IImagenRepository;
import ar.com.travelpaq.hogarpresente.api.cloudinary.service.CloudinaryService;
import ar.com.travelpaq.hogarpresente.api.cloudinary.service.IImagenService;
import ar.com.travelpaq.hogarpresente.api.models.dto.*;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.*;
import ar.com.travelpaq.hogarpresente.api.models.repository.IContenidoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUnidadRepository;
import ar.com.travelpaq.hogarpresente.api.security.repository.IUsuarioRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import ar.com.travelpaq.hogarpresente.api.security.enums.RoleNombre;
import ar.com.travelpaq.hogarpresente.api.security.mapper.UsuarioMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private IImagenRepository imagenRepository;

    @Autowired
    private IImagenService imagenService;

    @Autowired
    private IContenidoRepository contenidoRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    private static RoleEntity roleAdmin = new RoleEntity(3, RoleNombre.ROLE_ADMIN);

    private static RoleEntity roleCapacitador = new RoleEntity(2, RoleNombre.ROLE_CAPACITADOR);

    private static RoleEntity roleAlumno = new RoleEntity(1, RoleNombre.ROLE_ALUMNO);

    @Override
    public ResponseEntity<?> findAll() {
        List<CursoEntity> cursoEntities = cursoRepository.findAll();
        List<MostrarCursoDto> cursoDtos = new ArrayList<>();
        for (CursoEntity cursoEntity : cursoEntities){
            MostrarCursoDto cursoDto = cursoMapper.mapCursoDtoToCursoEntity(cursoEntity);
            cursoDtos.add(cursoDto);
        }
        return new ResponseEntity(cursoDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el curso en la base de datos"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.findById(id).get();
        MostrarCursoDto cursoDto = cursoMapper.mapCursoDtoToCursoEntity(cursoEntity);
        return new ResponseEntity(cursoDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(CursoDto newCurso) {
        if(StringUtils.isBlank(newCurso.getTitulo()))
            return new ResponseEntity(new Mensaje("El título es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newCurso.getSubtitulo()))
            return new ResponseEntity(new Mensaje("El subtítulo es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newCurso.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newCurso.getCategoria()))
            return new ResponseEntity(new Mensaje("La categoria es obligatoria"), HttpStatus.BAD_REQUEST);
        if(newCurso.getPrecio() < 0)
            return new ResponseEntity(new Mensaje("El precio no puede ser negativo"), HttpStatus.BAD_REQUEST);
        if(!usuarioRepository.existsById(newCurso.getUsuarioId()))
            return new ResponseEntity(new Mensaje("El usuario que crea el curso, no esta en la base de datos"), HttpStatus.NOT_FOUND);
        UsuarioEntity usuarioEntity = usuarioRepository.findById(newCurso.getUsuarioId()).get();
        if(usuarioEntity.getRoles().contains(roleAlumno))
            return new ResponseEntity(new Mensaje("El usuario debe ser un capacitador o admin para crear cursos"), HttpStatus.BAD_REQUEST);

        ImagenEntity imagenEntity = new ImagenEntity();
        if (newCurso.getImagenId() == 0)
            imagenEntity = imagenRepository.findById(2).get();
        else
            imagenEntity = imagenRepository.findById(newCurso.getImagenId()).get();
        CursoEntity cursoEntity = cursoMapper.mapCursoToCursoEntity(newCurso);
        cursoEntity.setHabilitado(false);
        cursoEntity.setImagen(imagenEntity);
        cursoEntity.setUsuario(usuarioEntity);
        cursoRepository.save(cursoEntity);
        usuarioEntity.getCursos().add(cursoEntity);
        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Curso creado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(CursoDto cursoDto, Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el curso en la base de datos"), HttpStatus.NOT_FOUND);
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
                            ,HttpStatus.BAD_REQUEST
                    );
        if (!cursoDto.getTitulo().isEmpty())
            cursoEntity.setTitulo(cursoDto.getTitulo());
        if (!cursoDto.getSubtitulo().isEmpty())
            cursoEntity.setSubtitulo(cursoDto.getSubtitulo());
        if (!cursoDto.getDescripcion().isEmpty())
            cursoEntity.setDescripcion(cursoDto.getDescripcion());
        if (!cursoDto.getCategoria().isEmpty())
            cursoEntity.setCategoria(cursoDto.getCategoria());
        if(cursoDto.getPrecio() >= 0)
            cursoEntity.setPrecio(cursoDto.getPrecio());
        if(cursoDto.getImagenId() != 0)
        {
            imagenRepository.deleteById(cursoEntity.getImagen().getId());
            ImagenEntity imagenEntity = imagenRepository.findById(cursoDto.getImagenId()).get();
            cursoEntity.setImagen(imagenEntity);
        }
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso Actualizado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        cursoRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Curso Eliminado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> habilitarCurso(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        if(cursoEntity.isHabilitado())
            cursoEntity.setHabilitado(false);
        else
            cursoEntity.setHabilitado(true);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso Habilitado!"), HttpStatus.OK);
    }
}
