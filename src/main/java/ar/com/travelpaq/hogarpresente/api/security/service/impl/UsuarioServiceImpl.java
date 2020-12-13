package ar.com.travelpaq.hogarpresente.api.security.service.impl;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.cloudinary.repository.IImagenRepository;
import ar.com.travelpaq.hogarpresente.api.cloudinary.service.CloudinaryService;
import ar.com.travelpaq.hogarpresente.api.security.dto.MuestraUsuarioDto;
import ar.com.travelpaq.hogarpresente.api.security.dto.UpdateUsuarioDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.security.mapper.UsuarioMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.security.repository.IUsuarioRepository;
import ar.com.travelpaq.hogarpresente.api.security.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

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
        List<MuestraUsuarioDto> usuarioDtos = new ArrayList<>();
        for (UsuarioEntity usuarioEntity : usuarioEntities) {
            MuestraUsuarioDto usuarioDto = usuarioMapper.mapUsuarioEntityToMuestraUsuarioDto(usuarioEntity);
            usuarioDtos.add(usuarioDto);
        }
        return new ResponseEntity(usuarioDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el usuario en la base de datos"), HttpStatus.NOT_FOUND);
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).get();
        MuestraUsuarioDto usuarioDto = usuarioMapper.mapUsuarioEntityToMuestraUsuarioDto(usuarioEntity);
        return new ResponseEntity(usuarioDto, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(UpdateUsuarioDto updateUsuarioDto, Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el usuario en la base de datos"), HttpStatus.NOT_FOUND);

        UsuarioEntity usuarioEntity = usuarioRepository.getOne(id);
        usuarioEntity.setNombre(updateUsuarioDto.getNombre());
        usuarioEntity.setApellido(updateUsuarioDto.getApellido());
        usuarioEntity.setCorreo(updateUsuarioDto.getCorreo());
        usuarioEntity.setClave(updateUsuarioDto.getClave());
        usuarioEntity.setEstudio(updateUsuarioDto.getEstudio());
        usuarioEntity.setEdad(updateUsuarioDto.getEdad());
        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Usuario Actualizado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        usuarioRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Usuario Eliminado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateImg(MultipartFile multipartFile, Long id) throws IOException {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el usuario al que se le desea subir la foto"), HttpStatus.NOT_FOUND);
        UsuarioEntity usuarioEntity = usuarioRepository.getOne(id);
        if (usuarioEntity.getImagen() != null && usuarioEntity.getImagen().getId() != 1)
        {
            imagenRepository.deleteById(usuarioEntity.getImagen().getId());
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
        usuarioEntity.setImagen(imagen);
        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Se actualizo la imagen correctamente"), HttpStatus.OK);
    }
}
