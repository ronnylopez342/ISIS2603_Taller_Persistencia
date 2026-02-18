package co.edu.uniandes.dse.TallerPersistencia.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;
import co.edu.uniandes.dse.TallerPersistencia.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.TallerPersistencia.repositories.PeliculaRepository;

@DataJpaTest
@Transactional
@Import(PeliculaService.class)
class PeliculaServiceTest {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    private List<PeliculaEntity> peliculaList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        peliculaRepository.deleteAll();
        peliculaList.clear();

        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setTitulo("Pelicula Existente");
        pelicula.setAnioLanzamiento(2000);
        pelicula = peliculaRepository.save(pelicula);
        peliculaList.add(pelicula);
    }

    @Test
    void testCrearPelicula() throws IllegalOperationException {
        PeliculaEntity nuevaPelicula = new PeliculaEntity();
        nuevaPelicula.setTitulo("Interstellar");
        nuevaPelicula.setAnioLanzamiento(2014);

        PeliculaEntity resultado = peliculaService.crearPelicula(nuevaPelicula);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals("Interstellar", resultado.getTitulo());
        assertEquals(2014, resultado.getAnioLanzamiento());
    }

    @Test
    void testCrearPeliculaNombreVacio() {
        PeliculaEntity nuevaPelicula = new PeliculaEntity();
        nuevaPelicula.setTitulo(" ");
        nuevaPelicula.setAnioLanzamiento(2010);

        assertThrows(IllegalOperationException.class, () -> {
            peliculaService.crearPelicula(nuevaPelicula);
        });
    }

    @Test
    void testCrearPeliculaNombreDuplicado() {
        PeliculaEntity nuevaPelicula = new PeliculaEntity();
        nuevaPelicula.setTitulo(peliculaList.get(0).getTitulo());
        nuevaPelicula.setAnioLanzamiento(2010);

        assertThrows(IllegalOperationException.class, () -> {
            peliculaService.crearPelicula(nuevaPelicula);
        });
    }

    @Test
    void testCrearPeliculaAnio1900() {
        PeliculaEntity nuevaPelicula = new PeliculaEntity();
        nuevaPelicula.setTitulo("Pelicula Antigua");
        nuevaPelicula.setAnioLanzamiento(1900);

        assertThrows(IllegalOperationException.class, () -> {
            peliculaService.crearPelicula(nuevaPelicula);
        });
    }

    @Test
    void testCrearPeliculaAnio1930() {
        PeliculaEntity nuevaPelicula = new PeliculaEntity();
        nuevaPelicula.setTitulo("Pelicula Limite");
        nuevaPelicula.setAnioLanzamiento(1930);

        assertThrows(IllegalOperationException.class, () -> {
            peliculaService.crearPelicula(nuevaPelicula);
        });
    }
}
