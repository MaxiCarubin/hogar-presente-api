package ar.com.travelpaq.hogarpresente.api.controller;
import ar.com.travelpaq.hogarpresente.api.models.dto.AlumnoDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AlumnoRestController {

    @Autowired
    private IAlumnoService alumnoService;

    @GetMapping("/alumnos")
    public ResponseEntity<List<AlumnoEntity>> findAll(){
        return alumnoService.findAll();
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        return alumnoService.findById(id);
    }

    @PostMapping("/alumnos")
    public ResponseEntity<?> create(@RequestBody AlumnoDto alumnoDto){
        return alumnoService.create(alumnoDto);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<?> update(@RequestBody AlumnoDto alumnoDto, @PathVariable Long id){
        return alumnoService.update(alumnoDto, id);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return alumnoService.delete(id);
    }

    /*
    @PostMapping("/alumnos/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();

        Alumno alumno = alumnoService.findById(id);

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

            String nombreFotoAnterior = alumno.getFoto();

            if (nombreFotoAnterior != null && nombreFotoAnterior.length()>0){
                Path rutaFotoAnterior = Paths.get("uploads").toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }

            }

            alumno.setFoto(nombreArchivo);

            alumnoService.update(alumno, id);

            response.put("alumno", alumno);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        Resource recurso = null;
        try{
            recurso = new UrlResource(rutaArchivo.toUri());
        }catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        if(!recurso.exists() && !recurso.isReadable()){
            throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

     */

}
