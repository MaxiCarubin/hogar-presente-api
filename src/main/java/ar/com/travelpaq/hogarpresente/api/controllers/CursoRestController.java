package ar.com.travelpaq.hogarpresente.api.controllers;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CursoRestController {
    @Autowired
    private ICursoService cursoService;

    @GetMapping("/cursos")
    public List<Curso> index(){
        return cursoService.findAll();
    }
    @GetMapping("/cursos/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return cursoService.findById(id);
    }

    @PostMapping("/cursos")
    public ResponseEntity<?> create(@RequestBody Curso curso){
        return cursoService.create(curso);
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<?> update(@RequestBody Curso curso, @PathVariable Long id){
        return cursoService.update(curso, id);
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<?> delente(@PathVariable Long id) {
        return cursoService.delete(id);
    }
}
