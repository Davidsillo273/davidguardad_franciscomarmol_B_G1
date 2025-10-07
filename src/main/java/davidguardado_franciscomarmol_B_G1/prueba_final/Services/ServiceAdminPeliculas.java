package davidguardado_franciscomarmol_B_G1.prueba_final.Services;

import davidguardado_franciscomarmol_B_G1.prueba_final.Entities.EntityAdminPeliculas;
import davidguardado_franciscomarmol_B_G1.prueba_final.Exceptions.ExceptionsNoEncontrado;
import davidguardado_franciscomarmol_B_G1.prueba_final.Models.DTO.DTOAdminPeliculas;
import davidguardado_franciscomarmol_B_G1.prueba_final.Repositories.RepositoryAdminPeliculas;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceAdminPeliculas {

    @Autowired
    private RepositoryAdminPeliculas repositoryAdminPeliculas;

    //Obtener

    public List<DTOAdminPeliculas> obtenerTodo() {
        return repositoryAdminPeliculas.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    //Insertar

    public DTOAdminPeliculas crear(@Valid DTOAdminPeliculas json) {
        EntityAdminPeliculas objEntity = convertirAEntity(json);
        EntityAdminPeliculas peliculaGuardada = repositoryAdminPeliculas.save(objEntity);
        return convertirADTO(peliculaGuardada);
    }

    //Actualizar

    public DTOAdminPeliculas actualizar(Long id, @Valid DTOAdminPeliculas json) {

        EntityAdminPeliculas peliculaExistente = repositoryAdminPeliculas.findById(id).orElseThrow(() -> new ExceptionsNoEncontrado("Pelicula no encontrado."));

        peliculaExistente.setTITULO(json.getTITULO());
        peliculaExistente.setDIRECTOR(json.getDIRECTOR());
        peliculaExistente.setGENERO(json.getGENERO());
        peliculaExistente.setANO_ESTRENO(json.getANO_ESTRENO());
        peliculaExistente.setDURACION_MIN(json.getDURACION_MIN());
        peliculaExistente.setFECHA_CREACION(json.getFECHA_CREACION());

        EntityAdminPeliculas pelicula = repositoryAdminPeliculas.save(peliculaExistente);

        return convertirADTO(pelicula);
    }

    //Eliminar

    public boolean eliminar(Long id) {

        EntityAdminPeliculas existente = repositoryAdminPeliculas.findById(id).orElse(null);

        if (existente != null) {
            repositoryAdminPeliculas.deleteById(id);
            return true;
        }
        return false;
    }


    //Métodos extras
    private DTOAdminPeliculas convertirADTO(EntityAdminPeliculas entityAdminPeliculas) {

        DTOAdminPeliculas dtoAdminPeliculas = new DTOAdminPeliculas();

        dtoAdminPeliculas.setID_PELICULA(entityAdminPeliculas.getID_PELICULA());
        dtoAdminPeliculas.setTITULO(entityAdminPeliculas.getTITULO());
        dtoAdminPeliculas.setDIRECTOR(entityAdminPeliculas.getDIRECTOR());
        dtoAdminPeliculas.setGENERO(entityAdminPeliculas.getGENERO());
        dtoAdminPeliculas.setANO_ESTRENO(entityAdminPeliculas.getANO_ESTRENO());
        dtoAdminPeliculas.setDURACION_MIN(entityAdminPeliculas.getDURACION_MIN());
        dtoAdminPeliculas.setFECHA_CREACION(entityAdminPeliculas.getFECHA_CREACION());

        return dtoAdminPeliculas;
    }

    private EntityAdminPeliculas convertirAEntity(@Valid DTOAdminPeliculas json) {

        EntityAdminPeliculas entityAdminPeliculas = new EntityAdminPeliculas();

        entityAdminPeliculas.setTITULO(json.getTITULO());
        entityAdminPeliculas.setDIRECTOR(json.getDIRECTOR());
        entityAdminPeliculas.setGENERO(json.getGENERO());
        entityAdminPeliculas.setANO_ESTRENO(json.getANO_ESTRENO());
        entityAdminPeliculas.setDURACION_MIN(json.getDURACION_MIN());
        entityAdminPeliculas.setFECHA_CREACION(json.getFECHA_CREACION());

        return entityAdminPeliculas;
    }
}
