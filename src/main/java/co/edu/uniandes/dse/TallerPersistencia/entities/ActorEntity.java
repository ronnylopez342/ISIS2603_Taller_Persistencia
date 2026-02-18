package co.edu.uniandes.dse.TallerPersistencia.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ActorEntity extends BaseEntity {

    private String nombre;

    private String nacionalidad;
}
