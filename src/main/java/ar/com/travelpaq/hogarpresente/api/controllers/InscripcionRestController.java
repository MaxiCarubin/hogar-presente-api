package ar.com.travelpaq.hogarpresente.api.controllers;

import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import ar.com.travelpaq.hogarpresente.api.models.services.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class InscripcionRestController {

    @Autowired
    private IInscripcionService inscripcionService;

    @GetMapping("/inscripciones")
    public List<Inscripcion> index(){
        return inscripcionService.findAll();
    }

    @PostMapping("/inscripciones")
    public ResponseEntity<?> create(@RequestBody Inscripcion inscripcion){
        return inscripcionService.create(inscripcion);
    }

    @DeleteMapping("/inscripciones/{id}")
    public ResponseEntity<?> delente(@PathVariable Long id) {
        return inscripcionService.delete(id);
    }
}
