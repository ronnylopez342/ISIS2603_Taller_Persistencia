package co.edu.uniandes.dse.TallerPersistencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;
import co.edu.uniandes.dse.TallerPersistencia.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.TallerPersistencia.repositories.PeliculaRepository;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Transactional
    public PeliculaEntity crearPelicula(PeliculaEntity peliculaEntity) throws IllegalOperationException {
        if (peliculaEntity.getNombre() == null || peliculaEntity.getNombre().isBlank()) {
            throw new IllegalOperationException("El nombre de la película no puede ser vacío");
        }

        if (peliculaRepository.findByNombre(peliculaEntity.getNombre()) != null) {
            throw new IllegalOperationException("Ya existe una película con el mismo nombre");
        }

        if (peliculaEntity.getAnio() == null || peliculaEntity.getAnio() <= 1930) {
            throw new IllegalOperationException("El año de la película debe ser mayor a 1930");
        }

        return peliculaRepository.save(peliculaEntity);
    }
}
