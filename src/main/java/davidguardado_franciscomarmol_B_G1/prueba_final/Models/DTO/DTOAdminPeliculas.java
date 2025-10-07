package davidguardado_franciscomarmol_B_G1.prueba_final.Models.DTO;

import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DTOAdminPeliculas {

    @Positive
    private Long ID_PELICULA;

    @Size(min = 15, max = 200)
    @NotBlank
    private String TITULO;

    @Size(min = 15, max = 120)
    @NotBlank
    private String DIRECTOR;

    @Size(min = 15, max = 60)
    private String GENERO;

    @Positive
    @NotNull
    private Long ANO_ESTRENO;

    @Positive
    private Long DURACION_MIN;

    @PastOrPresent
    private LocalDate FECHA_CREACION;
}
