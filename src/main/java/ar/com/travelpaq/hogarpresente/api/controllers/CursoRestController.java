package ar.com.travelpaq.hogarpresente.api.controllers;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/cursos/{nombre}")
    public Curso show(@PathVariable String nombre){
        return cursoService.findByNombre(nombre);
    }

    @PostMapping("/cursos")
    public Curso create(@RequestBody Curso curso){
        return cursoService.create(curso);
    }

    @PutMapping("/cursos/{id}")
    public Curso update(@RequestBody Curso curso, @PathVariable Long id){
        return cursoService.update(curso, id);
    }

    @DeleteMapping("/cursos/{id}")
    public void delente(@PathVariable Long id) {
        cursoService.delete(id);
    }
}
