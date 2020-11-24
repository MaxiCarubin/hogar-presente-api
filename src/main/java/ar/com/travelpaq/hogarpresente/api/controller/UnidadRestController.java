package ar.com.travelpaq.hogarpresente.api.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.services.IUnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UnidadRestController {
    @Autowired
    private IUnidadService unidadService;

    @GetMapping("/unidades")
    public ResponseEntity<List<UnidadDto>> index(){
        return unidadService.findAll();
    }

    @GetMapping("/unidades/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return unidadService.findById(id);
    }

    @PostMapping("/unidades")
    public ResponseEntity<?> create(@RequestBody UnidadDto unidadDto){
        return unidadService.create(unidadDto);
    }

    @PutMapping("/unidades/{id}")
    public ResponseEntity<?> update(@RequestBody UnidadDto unidadDto, @PathVariable Long id){
        return unidadService.update(unidadDto, id);
    }

    @DeleteMapping("/unidades/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return unidadService.delete(id);
    }

//    @GetMapping("/unidades/curso/{id}")
//    public ResponseEntity<?> findByCursoId(@PathVariable Long id){
//        return unidadService.findByCursoId(id);
//    }
}
