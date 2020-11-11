package ar.com.travelpaq.hogarpresente.api.controllers;

import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import ar.com.travelpaq.hogarpresente.api.models.services.IUnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UnidadRestController {
    @Autowired
    private IUnidadService unidadService;

    @GetMapping("/unidades")
    public List<Unidad> index(){
        return unidadService.findAll();
    }

    @GetMapping("/unidades/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return unidadService.findById(id);
    }

    @PostMapping("/unidades")
    public ResponseEntity<?> create(@RequestBody Unidad unidad){
        return unidadService.create(unidad);
    }

    @PutMapping("/unidades/{id}")
    public ResponseEntity<?> update(@RequestBody Unidad unidad, @PathVariable Long id){
        return unidadService.update(unidad, id);
    }

    @DeleteMapping("/unidades/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return unidadService.delete(id);
    }
}
