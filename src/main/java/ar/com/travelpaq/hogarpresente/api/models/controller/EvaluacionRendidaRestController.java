package ar.com.travelpaq.hogarpresente.api.models.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.NuevaEvaluacionRendidaDto;
import ar.com.travelpaq.hogarpresente.api.models.services.IEvaluacionRendidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EvaluacionRendidaRestController {

    @Autowired
    private IEvaluacionRendidaService evaluacionRendidaService;

    @GetMapping("/evaluacionesRendidas")
    public ResponseEntity<?> findAll(){
        return evaluacionRendidaService.findAll();
    }

    @PostMapping("/evaluacionesRendidas")
    public ResponseEntity<?> create(@RequestBody NuevaEvaluacionRendidaDto evaluacion){
        return evaluacionRendidaService.create(evaluacion);
    }

    @PutMapping("/evaluacionesRendidas/correccion/{id}")
    public ResponseEntity<?> updateCorreccion(@PathVariable Long id, @RequestBody Integer nota){
        return evaluacionRendidaService.updateCorreccion(nota, id);
    }

    @GetMapping("/evaluacionesRendidas/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        return evaluacionRendidaService.findById(id);
    }

    @DeleteMapping("/evaluacionesRendidas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return evaluacionRendidaService.delete(id);
    }




}
