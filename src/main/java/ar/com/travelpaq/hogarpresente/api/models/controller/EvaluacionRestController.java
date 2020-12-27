package ar.com.travelpaq.hogarpresente.api.models.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.NuevaEvaluacionDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.NuevoCursoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UpdateCursoDto;
import ar.com.travelpaq.hogarpresente.api.models.services.IEvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EvaluacionRestController {

    @Autowired
    private IEvaluacionService evaluacionService;

    @GetMapping("/evaluaciones")
    public ResponseEntity<?> findAll(){
        return evaluacionService.findAll();
    }

    @GetMapping("/evaluaciones/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        return evaluacionService.findById(id);
    }

    @PostMapping("/evaluaciones")
    public ResponseEntity<?> create(@RequestBody NuevaEvaluacionDto evaluacion){
        return evaluacionService.create(evaluacion);
    }

    @PutMapping("/evaluaciones/nombre/{id}")
    public ResponseEntity<?> updateNombre(@PathVariable Long id, @RequestBody String nombre){
        return evaluacionService.updateNombre(nombre, id);
    }

    @PutMapping("/evaluaciones/urlCapacitador/{id}")
    public ResponseEntity<?> updateUrlCapacitador(@PathVariable Long id, @RequestBody String urlCapacitador){
        return evaluacionService.updateUrlCapacitador(urlCapacitador, id);
    }

    @DeleteMapping("/evaluaciones/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return evaluacionService.delete(id);
    }

}
