package davidguardado_franciscomarmol_B_G1.prueba_final.Controller;

import davidguardado_franciscomarmol_B_G1.prueba_final.Exceptions.ExceptionsDuplicado;
import davidguardado_franciscomarmol_B_G1.prueba_final.Exceptions.ExceptionsNoEncontrado;
import davidguardado_franciscomarmol_B_G1.prueba_final.Models.DTO.DTOAdminPeliculas;
import davidguardado_franciscomarmol_B_G1.prueba_final.Services.ServiceAdminPeliculas;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/peliculas")
public class ControllerAdminPeliculas {

    @Autowired
    private ServiceAdminPeliculas serviceAdminPeliculas;

    @GetMapping("/obtenerTodoPeliculas")
    private List<DTOAdminPeliculas> ObtenerTodo(){
        return serviceAdminPeliculas.obtenerTodo();
    }

//Insertar
    @PostMapping("/nuevaPelicula")
    private ResponseEntity<Map<String, Object>> crearPelicula(@Valid @RequestBody DTOAdminPeliculas json, HttpServletRequest request) {
        try {
            DTOAdminPeliculas response = serviceAdminPeliculas.crear(json);
            if (response == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "Error", "Inserción incorrecta",
                        "Estatus", "Inserción incorrecta",
                        "Descripción", "Verifique los valores"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Estado", "Completado",
                    "data", response
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "error",
                            "message", "Error al registrar película",
                            "detail", e.getMessage()
                    ));
        }
    }
//Actualizar

    @PutMapping("/actualizarPelicula/{id}")
    public ResponseEntity<?> modificarPelicula(@PathVariable Long id, @Valid @RequestBody DTOAdminPeliculas peliculas, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            DTOAdminPeliculas peliculaActualizada = serviceAdminPeliculas.actualizar(id, peliculas);
            return ResponseEntity.ok(peliculaActualizada);
        } catch (ExceptionsNoEncontrado e) {
            return ResponseEntity.notFound().build();
        } catch (ExceptionsDuplicado e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of("error", "Datos duplicados","campo", e.getColumnDuplicate())
            );
        }
    }

    //Eliminar

    @DeleteMapping("/eliminarPelicula/{id}")
    public ResponseEntity<Map<String, Object>> eliminarPelicula(@PathVariable Long id) {
        try {

            if (!serviceAdminPeliculas.eliminar(id)) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("X-Mensaje-Error", "película no encontrada")

                        .body(Map.of(
                                "error", "Not found",
                                "mensaje", "La película no ha sido encontrada",
                                "timestamp", Instant.now().toString()
                        ));
            }

            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso completado",
                    "message", "Película eliminada exitosamente"
            ));

        } catch (Exception e) {

            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar la categoría",
                    "detail", e.getMessage()
            ));
        }
    }
}
