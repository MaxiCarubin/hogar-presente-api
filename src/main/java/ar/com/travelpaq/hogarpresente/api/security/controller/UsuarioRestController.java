package ar.com.travelpaq.hogarpresente.api.security.controller;
import ar.com.travelpaq.hogarpresente.api.security.dto.UpdateUsuarioDto;
import ar.com.travelpaq.hogarpresente.api.security.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<?> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateUsuarioDto updateUsuarioDto, @PathVariable Long id){
        return usuarioService.update(updateUsuarioDto, id);
    }

    @PutMapping("/usuarios/Img/{id}")
    public ResponseEntity<?> updateImg(@RequestParam MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        return usuarioService.updateImg(multipartFile, id);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return usuarioService.delete(id);
    }

}
