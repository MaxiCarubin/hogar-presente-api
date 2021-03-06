package ar.com.travelpaq.hogarpresente.api.cloudinary.service.impl;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.cloudinary.repository.IImagenRepository;
import ar.com.travelpaq.hogarpresente.api.cloudinary.service.CloudinaryService;
import ar.com.travelpaq.hogarpresente.api.cloudinary.service.IImagenService;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.security.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ImagenServiceImpl implements IImagenService {

    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ICursoRepository cursoRepository;

    @Override
    public ResponseEntity<?> list(){
        List<ImagenEntity> list = imagenRepository.findByOrderById();
        return new ResponseEntity(list, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<?> upload(MultipartFile multipartFile) throws IOException {
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
        return new ResponseEntity(imagen, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> delete(int id) throws IOException {
        if(!imagenRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        ImagenEntity imagen = imagenRepository.getOne(id);
        Map result = cloudinaryService.delete(imagen.getImagenId());
        imagenRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(int id) {
        if (!imagenRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe la imagen en la base de datos"), HttpStatus.NOT_FOUND);
        ImagenEntity imagenEntity = imagenRepository.findById(id).get();
        return new ResponseEntity(imagenEntity, HttpStatus.OK);
    }
}
