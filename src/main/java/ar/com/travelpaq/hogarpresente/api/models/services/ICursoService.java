package ar.com.travelpaq.hogarpresente.api.models.services;
import ar.com.travelpaq.hogarpresente.api.models.dto.NuevoCursoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UpdateCursoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICursoService {

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(NuevoCursoDto nuevoCursoDto);

    public ResponseEntity<?> update(UpdateCursoDto cursoDto, Long id);

    public ResponseEntity<?> delete(Long id);

    public ResponseEntity<?> habilitarOnOffCurso(Long id);

    public ResponseEntity<?> updateImg(MultipartFile multipartFile, Long id) throws IOException;

    public ResponseEntity<?> updateTitulo(String titulo, Long id);

    public ResponseEntity<?> updateSubtitulo(String subtitulo, Long id);

    public ResponseEntity<?> updateCategoria(String categoria, Long id);

    public ResponseEntity<?> updateDescripcion(String descripcion, Long id);

    public ResponseEntity<?> updatePrecio(float precio, Long id);
}
