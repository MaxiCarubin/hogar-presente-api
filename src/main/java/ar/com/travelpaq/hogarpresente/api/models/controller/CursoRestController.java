package ar.com.travelpaq.hogarpresente.api.models.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.NuevoCursoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UpdateCursoDto;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CursoRestController {

    @Autowired
    private ICursoService cursoService;

    @GetMapping("/cursos")
    public ResponseEntity<?> findAll(){
        return cursoService.findAll();
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        return cursoService.findById(id);
    }

    @PostMapping("/cursos")
    public ResponseEntity<?> create(@RequestBody NuevoCursoDto nuevoCursoDto){
        return cursoService.create(nuevoCursoDto);
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateCursoDto cursoDto, @PathVariable Long id){
        return cursoService.update(cursoDto, id);
    }

    @PutMapping("/cursos/Img/{id}")
    public ResponseEntity<?> updateImg(@RequestParam MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        return cursoService.updateImg(multipartFile, id);
    }

    @PutMapping("/cursos/titulo/{id}")
    public ResponseEntity<?> updateTitulo(@PathVariable Long id, @RequestBody String titulo){
        return cursoService.updateTitulo(titulo, id);
    }

    @PutMapping("/cursos/subtitulo/{id}")
    public ResponseEntity<?> updateSubtitulo(@PathVariable Long id, @RequestBody String subtitulo){
        return cursoService.updateSubtitulo(subtitulo, id);
    }

    @PutMapping("/cursos/categoria/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Long id, @RequestBody String categoria){
        return cursoService.updateCategoria(categoria, id);
    }

    @PutMapping("/cursos/descripcion/{id}")
    public ResponseEntity<?> updateDescripcion(@PathVariable Long id, @RequestBody String descripcion){
        return cursoService.updateDescripcion(descripcion, id);
    }

    @PutMapping("/cursos/precio/{id}")
    public ResponseEntity<?> updatePrecio(@PathVariable Long id, @RequestBody float precio){
        return cursoService.updatePrecio(precio, id);
    }


    @PutMapping("/cursos/habilitar/{id}")
    public ResponseEntity<?> habilitadoOnOffCurso(@PathVariable Long id){
        return cursoService.habilitarOnOffCurso(id);
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return cursoService.delete(id);
    }

    @GetMapping("/cursos/categorias")
    public ResponseEntity<?> listCategorias(){
        return cursoService.listCategorias();
    }

}
