package co.edu.uniandes.dse.TallerPersistencia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {
    Optional<PeliculaEntity> findByTitulo(String titulo);
}
