package ar.com.travelpaq.hogarpresente.api.cloudinary.controller;

import ar.com.travelpaq.hogarpresente.api.cloudinary.service.CloudinaryService;
import ar.com.travelpaq.hogarpresente.api.cloudinary.service.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class CloudinaryController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    private IImagenService imagenService;

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        return imagenService.list();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile)throws IOException {
        return imagenService.upload(multipartFile);
    }

    @PostMapping("/upload/img/curso")
    public ResponseEntity<?> uploadCurso(@RequestParam MultipartFile multipartFile, @RequestParam long idCurso)throws IOException {
        return imagenService.uploadCurso(multipartFile, idCurso);
    }

    @PostMapping("/upload/img/usuario")
    public ResponseEntity<?> uploadUsuario(@RequestParam MultipartFile multipartFile, @RequestParam long idUsuario)throws IOException {
        return imagenService.uploadUsuario(multipartFile, idUsuario);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
        return imagenService.delete(id);
    }
}
