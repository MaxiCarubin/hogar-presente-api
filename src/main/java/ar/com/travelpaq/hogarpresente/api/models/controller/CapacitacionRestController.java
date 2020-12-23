package ar.com.travelpaq.hogarpresente.api.models.controller;

import ar.com.travelpaq.hogarpresente.api.models.services.ICapacitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CapacitacionRestController {

    @Autowired
    private ICapacitacionService capacitacionService;

    @GetMapping("/capacitacion")
    public ResponseEntity<?> findAll(){
        return capacitacionService.findAll();
    }

    @GetMapping("/capacitacion/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        return capacitacionService.findById(id);
    }

//    @PostMapping("/capacitacion")
//    public ResponseEntity<?> create(@RequestBody NuevoCursoDto nuevoCursoDto){
//        return capacitacionService.create(nuevoCursoDto);
//    }
//
//    @PutMapping("/capacitacion/{id}")
//    public ResponseEntity<?> update(@RequestBody UpdateCursoDto cursoDto, @PathVariable Long id){
//        return capacitacionService.update(cursoDto, id);
//    }

    @DeleteMapping("/capacitacion/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return capacitacionService.delete(id);
    }
}
