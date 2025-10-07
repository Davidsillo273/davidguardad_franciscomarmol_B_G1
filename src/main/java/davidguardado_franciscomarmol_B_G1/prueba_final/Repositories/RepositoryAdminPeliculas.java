package davidguardado_franciscomarmol_B_G1.prueba_final.Repositories;

import davidguardado_franciscomarmol_B_G1.prueba_final.Entities.EntityAdminPeliculas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RepositoryAdminPeliculas extends JpaRepository<EntityAdminPeliculas, Long> {

}
