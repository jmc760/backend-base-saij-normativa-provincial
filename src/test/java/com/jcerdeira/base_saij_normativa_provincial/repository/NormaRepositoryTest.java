package com.jcerdeira.base_saij_normativa_provincial.repository;

import com.jcerdeira.base_saij_normativa_provincial.entity.Norma;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class NormaRepositoryTest {

    @Autowired
    NormaRepository normaRepository;

    private Norma createNorma() {
        return Norma.builder()
                .provinciaNombre("Provincia")
                .tipoNorma("Norma")
                .numeroNorma(123)
                .estadoVigencia("Vigencia")
                .fecha(LocalDate.of(1990, 4, 16))
                .fechaPublicacion(LocalDate.of(1990, 4, 17))
                .nombreNorma("Nombre")
                .tituloResumido("Resumido")
                .tituloSumario("Sumario")
                .informacionDigesto("Digesto")
                .textoActualizado("Actualizado")
                .build();
    }

    @Test
    public void testSaveNorma() {

        //arrange
        Norma norma = createNorma();

        //act
        Norma savedNorma = normaRepository.save(norma);

        //assert
        assertNotNull(savedNorma);
        assertEquals("Provincia", savedNorma.getProvinciaNombre());
    };

    @Test
    public void testDeleteNorma() {

        //arrange
        Norma norma = createNorma();

        //act
        normaRepository.save(norma);
        normaRepository.delete(norma);

        //assert
        Optional<Norma> deletedNorma = normaRepository.findById(norma.getId());
        assertFalse(deletedNorma.isPresent());
    }

    @Test
    public void findAllNormas() {

        //arrange
        Norma norma1 = createNorma();
        normaRepository.save(norma1);
        Norma norma2 = createNorma();
        normaRepository.save(norma2);

        //act
        List<Norma> normas = normaRepository.findAll();

        //assert
        assertEquals(2, normas.size());
    }

    @Test
    public void findNormaById() {

        //arrange
        Norma norma = createNorma();
        normaRepository.save(norma);

        //act
        Optional<Norma> foundNorma = normaRepository.findById(norma.getId());

        //assert
        assertTrue(foundNorma.isPresent());
        assertEquals(norma.getId(), foundNorma.get().getId());

    }

}
