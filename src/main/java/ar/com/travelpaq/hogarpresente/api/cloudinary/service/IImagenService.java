package ar.com.travelpaq.hogarpresente.api.cloudinary.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImagenService {

    public ResponseEntity<?> list();

    public ResponseEntity<?> upload(MultipartFile multipartFile) throws IOException;

    public ResponseEntity<?> delete(int id) throws IOException;

    public ResponseEntity<?> findById(int id);

//    public ResponseEntity<?> uploadCurso(MultipartFile multipartFile, long idCurso);

//    public ResponseEntity<?> uploadUsuario(MultipartFile multipartFile, long idUsuario);

}
