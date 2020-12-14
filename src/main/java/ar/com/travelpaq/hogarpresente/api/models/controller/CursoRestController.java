package ar.com.travelpaq.hogarpresente.api.models.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.NuevoCursoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UpdateCursoDto;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CursoRestController {

    @Autowired
    private ICursoService cursoService;

    @GetMapping("/cursos")
    public ResponseEntity<?> findAll(){
        return cursoService.findAll();
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        return cursoService.findById(id);
    }

    @PostMapping("/cursos")
    public ResponseEntity<?> create(@RequestBody NuevoCursoDto nuevoCursoDto){
        return cursoService.create(nuevoCursoDto);
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateCursoDto cursoDto, @PathVariable Long id){
        return cursoService.update(cursoDto, id);
    }

    @PutMapping("/cursos/Img/{id}")
    public ResponseEntity<?> updateImg(@RequestParam MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        return cursoService.updateImg(multipartFile, id);
    }

    @PutMapping("/cursos/habilitar/{id}")
    public ResponseEntity<?> habilitadoOn(@PathVariable Long id){
        return cursoService.habilitarCurso(id);
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return cursoService.delete(id);
    }

}
