package co.edu.uniandes.dse.TallerPersistencia.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PeliculaEntity extends BaseEntity {

    private String nombre;

    private Integer anio;
}
