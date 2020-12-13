package ar.com.travelpaq.hogarpresente.api.models.services;
import ar.com.travelpaq.hogarpresente.api.models.dto.NuevoCursoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICursoService {

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(NuevoCursoDto nuevoCursoDto);

    public ResponseEntity<?> update(NuevoCursoDto nuevoCursoDto, Long id);

    public ResponseEntity<?> delete(Long id);

    public ResponseEntity<?> habilitarCurso(Long id);

    public ResponseEntity<?> updateImg(MultipartFile multipartFile, Long id) throws IOException;
}
