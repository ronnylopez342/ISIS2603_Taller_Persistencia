package co.edu.uniandes.dse.TallerPersistencia.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;
import co.edu.uniandes.dse.TallerPersistencia.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.TallerPersistencia.repositories.PeliculaRepository;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    @Transactional
    public PeliculaEntity crearPelicula(PeliculaEntity peliculaEntity) throws IllegalOperationException {
        if (peliculaEntity == null || peliculaEntity.getTitulo() == null || peliculaEntity.getTitulo().isBlank()) {
            throw new IllegalOperationException("El nombre de la pelicula no puede ser vacio");
        }

        if (peliculaRepository.findByTitulo(peliculaEntity.getTitulo()).isPresent()) {
            throw new IllegalOperationException("Ya existe una pelicula con ese nombre");
        }

        if (peliculaEntity.getAnioLanzamiento() == null || peliculaEntity.getAnioLanzamiento() <= 1930) {
            throw new IllegalOperationException("El anio de la pelicula debe ser mayor a 1930");
        }

        return peliculaRepository.save(peliculaEntity);
    }
}

