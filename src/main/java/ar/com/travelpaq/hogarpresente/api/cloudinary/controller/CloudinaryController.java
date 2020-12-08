package ar.com.travelpaq.hogarpresente.api.cloudinary.controller;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.cloudinary.service.CloudinaryService;
import ar.com.travelpaq.hogarpresente.api.cloudinary.service.ImagenService;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class CloudinaryController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImagenService imagenService;

    @GetMapping("/list")
    public ResponseEntity<List<ImagenEntity>> list(){
        List<ImagenEntity> list = imagenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile)throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Mensaje("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        ImagenEntity imagen =
                new ImagenEntity((String)result.get("original_filename"),
                        (String)result.get("url"),
                        (String)result.get("public_id"));
        imagenService.save(imagen);
        return new ResponseEntity(new Mensaje("imagen subida"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
        if(!imagenService.exists(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        ImagenEntity imagen = imagenService.getOne(id).get();
        Map result = cloudinaryService.delete(imagen.getImagenId());
        imagenService.delete(id);
        return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
    }
}
