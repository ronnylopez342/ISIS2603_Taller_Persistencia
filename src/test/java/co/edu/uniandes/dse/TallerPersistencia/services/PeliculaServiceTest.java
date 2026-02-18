package co.edu.uniandes.dse.TallerPersistencia.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;
import co.edu.uniandes.dse.TallerPersistencia.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.TallerPersistencia.repositories.PeliculaRepository;

@DataJpaTest
@Import(PeliculaService.class)
class PeliculaServiceTest {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    void crearPeliculaOk() throws IllegalOperationException {
        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setNombre("Inception");
        pelicula.setAnio(2010);

        PeliculaEntity creada = peliculaService.crearPelicula(pelicula);

        assertNotNull(creada.getId());
        assertEquals("Inception", creada.getNombre());
        assertEquals(2010, creada.getAnio());
    }

    @Test
    void crearPeliculaFallaPorNombreVacio() {
        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setNombre("   ");
        pelicula.setAnio(2000);

        assertThrows(IllegalOperationException.class, () -> peliculaService.crearPelicula(pelicula));
    }

    @Test
    void crearPeliculaFallaPorNombreRepetido() throws IllegalOperationException {
        PeliculaEntity existente = new PeliculaEntity();
        existente.setNombre("Avatar");
        existente.setAnio(2009);
        peliculaRepository.save(existente);

        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setNombre("Avatar");
        pelicula.setAnio(2022);

        assertThrows(IllegalOperationException.class, () -> peliculaService.crearPelicula(pelicula));
    }

    @Test
    void crearPeliculaFallaPorAnioInvalido() {
        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setNombre("Metropolis");
        pelicula.setAnio(1927);

        assertThrows(IllegalOperationException.class, () -> peliculaService.crearPelicula(pelicula));
    }
}
