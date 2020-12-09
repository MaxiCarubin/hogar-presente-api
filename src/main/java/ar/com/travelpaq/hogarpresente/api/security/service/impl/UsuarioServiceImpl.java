package ar.com.travelpaq.hogarpresente.api.security.service.impl;

import ar.com.travelpaq.hogarpresente.api.security.dto.CompletoUsuarioDto;
import ar.com.travelpaq.hogarpresente.api.security.dto.UsuarioDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.security.mapper.UsuarioMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.security.repository.IUsuarioRepository;
import ar.com.travelpaq.hogarpresente.api.security.service.IUsuarioService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Override
    public Optional<UsuarioEntity> getByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    public void save(UsuarioEntity usuarioEntity) {
        usuarioRepository.save(usuarioEntity);
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<UsuarioEntity> usuarioEntities = usuarioRepository.findAll();
        List<CompletoUsuarioDto> usuarioDtos = new ArrayList<>();
        for (UsuarioEntity usuarioEntity : usuarioEntities) {
            CompletoUsuarioDto usuarioDto = usuarioMapper.mapUsuarioEntityToUsuarioDto(usuarioEntity);
            usuarioDtos.add(usuarioDto);
        }
        return new ResponseEntity(usuarioDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el alumno en la base de datos"), HttpStatus.NOT_FOUND);
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).get();
        CompletoUsuarioDto usuarioDto = usuarioMapper.mapUsuarioEntityToUsuarioDto(usuarioEntity);
        return new ResponseEntity(usuarioDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(UsuarioDto usuarioDto) {
        if (StringUtils.isBlank(usuarioDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(usuarioDto.getApellido()))
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isEmpty(usuarioDto.getClave()))
            return new ResponseEntity(new Mensaje("La clave no puede estar vacia"), HttpStatus.BAD_REQUEST);
        if (usuarioRepository.existsByCorreo(usuarioDto.getCorreo()))
            return new ResponseEntity(new Mensaje("Ese correo ya existe"), HttpStatus.BAD_REQUEST);
        UsuarioEntity usuarioEntity = usuarioMapper.mapAlumnoToAlumnoEntity(usuarioDto);
        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Alumno creado"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(UsuarioDto usuarioDto, Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el alumno en la base de datos"), HttpStatus.NOT_FOUND);
        UsuarioEntity usuarioEntity = usuarioRepository.getOne(id);
        usuarioEntity.setNombre(usuarioDto.getNombre());
        usuarioEntity.setApellido(usuarioDto.getApellido());
        usuarioEntity.setCorreo(usuarioDto.getCorreo());
        usuarioEntity.setClave(usuarioDto.getClave());
        usuarioEntity.setEdad(usuarioDto.getEdad());
        usuarioEntity.setEstudio(usuarioDto.getEstudio());

//        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();
//        Set<InscripcionDto> inscripcionesDto = alumnoDto.getInscripciones();
//        for (InscripcionDto inscripcionDto : inscripcionesDto) {
//            inscripcionesEntity.add(inscripcionMapper.mapInscripcionToInscripcionEntity(inscripcionDto));
//        }
//        alumnoEntity.setInscripciones(inscripcionesEntity);

        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Alumno Actualizado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        usuarioRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Alumno Eliminado!"), HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<?> upload(MultipartFile archivo, Long id) {
//        UsuarioEntity usuarioEntity = usuarioRepository.getOne(id);
//        if (!archivo.isEmpty()) {
//            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
//            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
//            try {
//                Files.copy(archivo.getInputStream(), rutaArchivo);
//            } catch (IOException e) {
//                return new ResponseEntity(new Mensaje("Error al subir la imagen del alumno. " + nombreArchivo + "\n Error: " + e.getMessage().concat(": ").concat(e.getCause().getMessage())), HttpStatus.BAD_REQUEST);
//            }
//            String nombreFotoAnterior = usuarioEntity.getFoto();
//            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
//                Path rutaFotoAnterior = Paths.get("uploads").toAbsolutePath();
//                File archivoFotoAnterior = rutaFotoAnterior.toFile();
//                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
//                    archivoFotoAnterior.delete();
//                }
//            }
//            usuarioEntity.setFoto(nombreArchivo);
//            usuarioRepository.save(usuarioEntity);
//            return new ResponseEntity(new Mensaje("Has subido correctamente la imagen: " + nombreArchivo), HttpStatus.CREATED);
//        }
//        return new ResponseEntity(new Mensaje("El archivo se encuentra vacio!"), HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    public ResponseEntity<?> verFoto(String nombreFoto) {
//        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
//        Resource resource = null;
//        try{
//            resource = new UrlResource(rutaArchivo.toUri());
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }
//        if (!resource.exists() && !resource.isReadable()){
//            throw new RuntimeException("Error no se pudo cargar la imagen: " +nombreFoto);
//        }
//        HttpHeaders cabecera = new HttpHeaders();
//        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + resource.getFilename() +  "\"");
//        return new ResponseEntity(resource, cabecera, HttpStatus.OK);
//    }


    //    @GetMapping("/uploads/img/{nombreFoto:.+}")
//    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
//        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
//        Resource recurso = null;
//        try{
//            recurso = new UrlResource(rutaArchivo.toUri());
//        }catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }
//        if(!recurso.exists() && !recurso.isReadable()){
//            throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
//        }
//        HttpHeaders cabecera = new HttpHeaders();
//        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + recurso.getFilename() + "\"");
//        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
//    }

}
