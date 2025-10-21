package com.jcerdeira.base_saij_normativa_provincial.service;

import com.jcerdeira.base_saij_normativa_provincial.entity.Norma;
import com.jcerdeira.base_saij_normativa_provincial.exception.NormaNotFoundException;
import com.jcerdeira.base_saij_normativa_provincial.repository.NormaRepository;
import com.jcerdeira.base_saij_normativa_provincial.service.impl.NormaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NormaServiceTest {

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

    @Mock
    private NormaRepository normaRepository;

    @InjectMocks
    private NormaServiceImpl normaServiceImpl;

    @Test
    public void testFindNormaById() {

        //arrange
        Norma norma = createNorma();
        when(normaRepository.findById(1L)).thenReturn(Optional.of(norma));

        //act
        Norma foundNorma = normaServiceImpl.findNormaById(1L);

        //assert
        assertEquals(norma, foundNorma);
    }

    @Test
    public void testFindNormaById_NormaNotFound() {

        //arrange
        Long nonExistentId = 99L;
        when(normaRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        //act & assert
        assertThrows(NormaNotFoundException.class, ()-> normaServiceImpl.findNormaById(nonExistentId));
        verify(normaRepository, times(1)).findById(nonExistentId);
    }

    @Test
    public void testFindAllNormas() {

        //arrange
        List<Norma> normas = Arrays.asList( createNorma(), createNorma(), createNorma() );
        when(normaRepository.findAll()).thenReturn(normas);

        //act
        List<Norma> retrievedNormas = normaServiceImpl.findAllNormas();

        //assert
        assertEquals(normas, retrievedNormas);
        assertEquals(3, retrievedNormas.size());
        verify(normaRepository, times(1)).findAll();
    }

    @Test
    public void testCreateNorma() {

        //arrange
        Norma norma = createNorma();
        when(normaRepository.save(any(Norma.class))).thenReturn(norma);

        //act
        Norma createdNorma = normaServiceImpl.createNorma(norma);

        //assert
        assertEquals(norma, createdNorma);
        verify(normaRepository, times(1)).save(any(Norma.class));
    }

    @Test
    public void testDeleteNorma() {

        // Arrange
        Long normaId = 1L;
        Norma existingNorma = createNorma();
        when(normaRepository.findById(normaId)).thenReturn(Optional.of(existingNorma));
        doNothing().when(normaRepository).deleteById(normaId);

        // Act
        normaServiceImpl.deleteNormaById(normaId);

        // Assert
        verify(normaRepository, times(1)).findById(normaId);
        verify(normaRepository, times(1)).deleteById(normaId);
    }

    @Test
    public void testDeleteNorma_NormaNotFound() {

        // Arrange
        Long normaId = 1L;
        when(normaRepository.findById(normaId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NormaNotFoundException.class, ()-> normaServiceImpl.deleteNormaById(1L));
        verify(normaRepository, times(1)).findById(normaId);
    }
}
