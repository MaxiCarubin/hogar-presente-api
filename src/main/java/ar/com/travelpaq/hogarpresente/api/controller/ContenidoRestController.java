package ar.com.travelpaq.hogarpresente.api.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.ContenidoDto;
import ar.com.travelpaq.hogarpresente.api.models.services.IContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ContenidoRestController {

    @Autowired
    private IContenidoService tareaService;

    @GetMapping("/contenidos")
    public ResponseEntity<List<ContenidoDto>> index(){
        return tareaService.findAll();
    }

    @GetMapping("/contenidos/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return tareaService.findById(id);
    }

    @PostMapping("/contenidos")
    public ResponseEntity<?> create(@RequestBody ContenidoDto contenidoDto){
        return tareaService.create(contenidoDto);
    }

    @PutMapping("/contenidos/{id}")
    public ResponseEntity<?> update(@RequestBody ContenidoDto contenidoDto, @PathVariable Long id){
        return tareaService.update(contenidoDto, id);
    }

    @DeleteMapping("/contenidos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return tareaService.delete(id);
    }

//    @GetMapping("/tareas/unidad/{id}")
//    public  ResponseEntity<?> findByUnidadId(@PathVariable Long id){
//        return tareaService.findByUnidadId(id);
//    }
}
