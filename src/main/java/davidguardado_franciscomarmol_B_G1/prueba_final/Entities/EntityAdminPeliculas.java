package davidguardado_franciscomarmol_B_G1.prueba_final.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import davidguardado_franciscomarmol_B_G1.prueba_final.Models.DTO.DTOAdminPeliculas;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode

@Table(name = "ADMIN.PELICULAS")
public class EntityAdminPeliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PELICULAS")
    @SequenceGenerator(sequenceName = "SEQ_PELICULAS", name = "SEQ_PELICULAS", allocationSize = 1)
    private Long ID_PELICULA;

    @Column(name = "TITULO")
    private String TITULO;

    @Column(name = "DIRECTOR")
    private String DIRECTOR;

    @Column(name = "GENERO")
    private String GENERO;

    @Column(name = "ANO_ESTRENO")
    private Long ANO_ESTRENO;

    @Column(name = "DURACION_MIN")
    private Long DURACION_MIN;

    @Column(name = "FECHA_CREACION")
    private LocalDate FECHA_CREACION;

    @Override
    public String toString() {
        return "EntityAdminPeliculas{" +
                "ID_PELICULA=" + ID_PELICULA +
                ", TITULO='" + TITULO + '\'' +
                ", DIRECTOR='" + DIRECTOR + '\'' +
                ", GENERO='" + GENERO + '\'' +
                ", ANO_ESTRENO=" + ANO_ESTRENO +
                ", DURACION_MIN=" + DURACION_MIN +
                ", FECHA_CREACION=" + FECHA_CREACION +
                '}';
    }
}
