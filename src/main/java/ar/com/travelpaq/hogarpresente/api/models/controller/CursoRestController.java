package ar.com.travelpaq.hogarpresente.api.models.controller;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.models.dto.CursoDto;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> create(@RequestBody CursoDto cursoDto){
        return cursoService.create(cursoDto);
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<?> update(@RequestBody CursoDto cursoDto, @PathVariable Long id){
        return cursoService.update(cursoDto, id);
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
