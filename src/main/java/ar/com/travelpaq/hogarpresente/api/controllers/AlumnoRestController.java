package ar.com.travelpaq.hogarpresente.api.controllers;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class AlumnoRestController {

    @Autowired
    private IAlumnoService alumnoService;

    @GetMapping("/alumnos")
    public List<AlumnoEntity> index(){
        return alumnoService.findAll();
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        AlumnoEntity alumnoEntity = null;

        Map<String,Object> response = new HashMap<>();

        try {
            alumnoEntity = alumnoService.findById(id);

        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(alumnoEntity == null){
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!!!")));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(alumnoEntity, HttpStatus.OK);
    }

    @PostMapping("/alumnos")
    public ResponseEntity<?> create(@RequestBody AlumnoEntity alumnoEntity){

        AlumnoEntity alumnoEntityNew = null;

        Map<String,Object> response = new HashMap<>();

        try{
            alumnoEntityNew = alumnoService.save(alumnoEntity);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido creado con éxito!");
        response.put("alumno", alumnoEntityNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<?> update(@RequestBody AlumnoEntity alumnoEntity, @PathVariable Long id){

        AlumnoEntity alumnoEntityActual = alumnoService.findById(id);
        AlumnoEntity alumnoEntityUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if (alumnoEntityActual==null){
            response.put("mensaje","Error: no se pudo editar, el cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{

            alumnoEntityActual.setApellido(alumnoEntity.getApellido());
            alumnoEntityActual.setClave(alumnoEntity.getClave());
            alumnoEntityActual.setCorreo(alumnoEntity.getCorreo());
            alumnoEntityActual.setNombre(alumnoEntity.getCorreo());

            alumnoEntityUpdate = alumnoService.save(alumnoEntityActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar el alumno en la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido actualizado con éxito!");

        response.put("alumno", alumnoEntityUpdate);

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<?> delente(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        try{
            alumnoService.delete(id);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al eliminar el alumno en la base de datos. ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido eliminado con exito! ");

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/alumnos/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();

        AlumnoEntity alumnoEntity = alumnoService.findById(id);

        if (!archivo.isEmpty()){
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ","");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

            try{
                Files.copy(archivo.getInputStream(), rutaArchivo);
            }catch (IOException e){
                response.put("mensaje", "Error al subir la imagen del alumno. " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreFotoAnterior = alumnoEntity.getFoto();

            if (nombreFotoAnterior != null && nombreFotoAnterior.length() >0){
                Path
            }

            alumnoEntity.setFoto(nombreArchivo);

            alumnoService.save(alumnoEntity);

            response.put("alumno", alumnoEntity);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
