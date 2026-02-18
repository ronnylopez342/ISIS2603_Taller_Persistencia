package co.edu.uniandes.dse.TallerPersistencia.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class PeliculaEntity extends BaseEntity {

    private String titulo;

    private Integer anioLanzamiento;
}
