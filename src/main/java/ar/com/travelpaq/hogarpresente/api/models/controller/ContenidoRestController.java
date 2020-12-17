package ar.com.travelpaq.hogarpresente.api.models.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.ContenidoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.NuevoContenidoDto;
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
    private IContenidoService contenidoService;

    @GetMapping("/contenidos")
    public ResponseEntity<List<ContenidoDto>> index(){
        return contenidoService.findAll();
    }

    @GetMapping("/contenidos/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        return contenidoService.findById(id);
    }

    @PostMapping("/contenidos")
    public ResponseEntity<?> create(@RequestBody ContenidoDto contenidoDto){
        return contenidoService.create(contenidoDto);
    }

    @PostMapping("/contenidos/null")
    public ResponseEntity<?> createNull(@RequestBody NuevoContenidoDto contenidoDto){
        return contenidoService.createNull(contenidoDto);
    }

    @PutMapping("/contenidos/{id}")
    public ResponseEntity<?> update(@RequestBody ContenidoDto contenidoDto, @PathVariable Long id){
        return contenidoService.update(contenidoDto, id);
    }

    @PutMapping("/contenidos/nombre/{id}")
    public ResponseEntity<?> updateNombre(@PathVariable Long id, @RequestBody String nombre){
        return contenidoService.updateNombre(nombre, id);
    }

    @PutMapping("/contenidos/descripcion/{id}")
    public ResponseEntity<?> updateDescripcion(@PathVariable Long id, @RequestBody String descripcion){
        return contenidoService.updateDescripcion(descripcion, id);
    }

    @PutMapping("/contenidos/terminado/{id}")
    public ResponseEntity<?> TerminadoOnOff(@PathVariable Long id){
        return contenidoService.terminarOnOffCurso(id);
    }

    @DeleteMapping("/contenidos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return contenidoService.delete(id);
    }

}
