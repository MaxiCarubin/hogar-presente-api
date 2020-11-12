package ar.com.travelpaq.hogarpresente.api.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.InscripcionDto;
import ar.com.travelpaq.hogarpresente.api.models.services.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class InscripcionRestController {

    @Autowired
    private IInscripcionService inscripcionService;

    @GetMapping("/inscripciones")
    public ResponseEntity<List<InscripcionDto>> index(){
        return inscripcionService.findAll();
    }

    @GetMapping("/inscripciones/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return inscripcionService.findById(id);
    }

    @PostMapping("/inscripciones")
    public ResponseEntity<?> create(@RequestBody InscripcionDto inscripcionDto){
        return inscripcionService.create(inscripcionDto);
    }

    @PutMapping("/inscripciones/{id}")
    public ResponseEntity<?> update(@RequestBody InscripcionDto inscripcionDto, @PathVariable Long id){
        return inscripcionService.update(inscripcionDto, id);
    }

    @DeleteMapping("/inscripciones/{id}")
    public ResponseEntity<?> delente(@PathVariable Long id) {
        return inscripcionService.delete(id);
    }
}
