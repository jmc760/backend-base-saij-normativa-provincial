package com.jcerdeira.base_saij_normativa_provincial.service;

import com.jcerdeira.base_saij_normativa_provincial.entity.Norma;

import java.util.List;

public interface NormaService {

    Norma findNormaById(Long id);

    List<Norma> findAllNormas();

    Norma createNorma(Norma norma);

    void deleteNormaById(Long id);
}
