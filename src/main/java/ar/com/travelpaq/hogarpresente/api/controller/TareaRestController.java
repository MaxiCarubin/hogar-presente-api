package ar.com.travelpaq.hogarpresente.api.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.TareaDto;
import ar.com.travelpaq.hogarpresente.api.models.services.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TareaRestController {

    @Autowired
    private ITareaService tareaService;

    @GetMapping("/tareas")
    public ResponseEntity<List<TareaDto>> index(){
        return tareaService.findAll();
    }

    @GetMapping("/tareas/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return tareaService.findById(id);
    }

    @PostMapping("/tareas")
    public ResponseEntity<?> create(@RequestBody TareaDto tareaDto){
        return tareaService.create(tareaDto);
    }

    @PutMapping("/tareas/{id}")
    public ResponseEntity<?> update(@RequestBody TareaDto tareaDto, @PathVariable Long id){
        return tareaService.update(tareaDto, id);
    }

    @DeleteMapping("/tareas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return tareaService.delete(id);
    }

//    @GetMapping("/tareas/unidad/{id}")
//    public  ResponseEntity<?> findByUnidadId(@PathVariable Long id){
//        return tareaService.findByUnidadId(id);
//    }
}
