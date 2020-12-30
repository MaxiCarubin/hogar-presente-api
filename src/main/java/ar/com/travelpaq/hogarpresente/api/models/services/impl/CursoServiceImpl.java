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
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<CursoEntity> cursoEntities = cursoRepository.findByOrderById();
        List<MostrarCursoDto> cursoDtos = new ArrayList<>();
        for (CursoEntity cursoEntity : cursoEntities){
            MostrarCursoDto cursoDto = cursoMapper.mapMostrarCursoDtoToCursoEntity(cursoEntity);
            cursoDtos.add(cursoDto);
        }
        return new ResponseEntity(cursoDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el curso en la base de datos"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.findById(id).get();
        MostrarCursoDto cursoDto = cursoMapper.mapMostrarCursoDtoToCursoEntity(cursoEntity);
        return new ResponseEntity(cursoDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(NuevoCursoDto newCurso) {
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
        ImagenEntity imagenEntity = imagenRepository.findById(2).get();
        CursoEntity cursoEntity = cursoMapper.mapNuevoCursoDtoToCursoEntity(newCurso);
        cursoEntity.setHabilitado(false);
        cursoEntity.setUsuario(usuarioEntity);
        cursoEntity.setImagen(imagenEntity);
        cursoRepository.save(cursoEntity);
        usuarioEntity.getCursos().add(cursoEntity);
        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Curso creado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(UpdateCursoDto cursoDto, Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el curso en la base de datos"), HttpStatus.NOT_FOUND);
//        UsuarioEntity usuarioNuevo = usuarioRepository.findById(cursoDto.getUsuarioId()).get();
//        if(usuarioNuevo.getRoles().contains(roleAlumno))
//            return new ResponseEntity(new Mensaje("El usuario debe ser un capacitador o admin para editar el curso"), HttpStatus.BAD_REQUEST);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
//        UsuarioEntity usuarioViejo = cursoRepository.findById(id).get().getUsuario();
//        if (usuarioNuevo.getId() != usuarioViejo.getId() && usuarioNuevo.getRoles().contains(roleCapacitador))
//            return new ResponseEntity
//                    (
//                            new Mensaje
//                                    (
//                                            "El ID del creador del curso que se quiere modificar debe coincidir con el ID del usuario que esta modificando el curso o ese usuario debe ser ADMIN."
//                                    )
//                            ,HttpStatus.BAD_REQUEST
//                    );
        cursoEntity.setTitulo(cursoDto.getTitulo());
        cursoEntity.setSubtitulo(cursoDto.getSubtitulo());
        cursoEntity.setDescripcion(cursoDto.getDescripcion());
        cursoEntity.setCategoria(cursoDto.getCategoria());
        cursoEntity.setPrecio(cursoDto.getPrecio());
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
    public ResponseEntity<?> habilitarOnOffCurso(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        Mensaje mensaje = new Mensaje();
        if(cursoEntity.isHabilitado()) {
            cursoEntity.setHabilitado(false);
            mensaje.setMensaje("Curso deshabilitado!");
        }
        else{
            cursoEntity.setHabilitado(true);
            mensaje.setMensaje("Curso habilitado!");
        }
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(mensaje, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateImg(MultipartFile multipartFile, Long id) throws IOException {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el curso al que se le desea subir la foto"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        if (cursoEntity.getImagen() != null && cursoEntity.getImagen().getId() != 2)
        {
            imagenRepository.deleteById(cursoEntity.getImagen().getId());
        }
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Mensaje("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        ImagenEntity imagen =
                new ImagenEntity(
                        (String)result.get("original_filename"),
                        (String)result.get("url"),
                        (String)result.get("public_id")
                );
        imagenRepository.save(imagen);
        cursoEntity.setImagen(imagen);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Se actualizo la imagen correctamente"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateTitulo(String titulo, Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        cursoEntity.setTitulo(titulo);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso Actualizado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateSubtitulo(String subtitulo, Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        cursoEntity.setSubtitulo(subtitulo);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso Actualizado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateCategoria(String categoria, Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        cursoEntity.setCategoria(categoria);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso Actualizado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateDescripcion(String descripcion, Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        cursoEntity.setDescripcion(descripcion);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso Actualizado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updatePrecio(float precio, Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        cursoEntity.setPrecio(precio);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso Actualizado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listCategorias(){
        List<CursoEntity> cursoEntities = cursoRepository.findByOrderById();
        List<String> categorias = new ArrayList<>();
        for (CursoEntity cursoEntity : cursoEntities){
            if (!categorias.contains(cursoEntity.getCategoria()))
                categorias.add(cursoEntity.getCategoria());
        }
        return new ResponseEntity(categorias, HttpStatus.OK);
    }
}
