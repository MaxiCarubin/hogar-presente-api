package ar.com.travelpaq.hogarpresente.api.controller;
import ar.com.travelpaq.hogarpresente.api.models.dto.UsuarioDto;
import ar.com.travelpaq.hogarpresente.api.models.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/usuarios")
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto){
        return usuarioService.create(usuarioDto);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable Long id){
        return usuarioService.update(usuarioDto, id);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return usuarioService.delete(id);
    }

//    @PostMapping("/usuarios/upload")
//    public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") Long id){
//        return usuarioService.upload(archivo, id);
//    }
//    @GetMapping("/uploads/img/{nombreFoto:.+}")
//    public ResponseEntity<?> verFoto(@PathVariable String nombreFoto){
//        return usuarioService.verFoto(nombreFoto);
//    }
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

//    @GetMapping("/uploads/img/{nombreFoto:.+}")
//    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
//        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
//        Resource recurso = null;
//        try{
//            recurso = new UrlResource(rutaArchivo.toUri());
//        }catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }
//        if(!recurso.exists() && !recurso.isReadable()){
//            throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
//        }
//        HttpHeaders cabecera = new HttpHeaders();
//        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + recurso.getFilename() + "\"");
//        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
//    }

     */

}
