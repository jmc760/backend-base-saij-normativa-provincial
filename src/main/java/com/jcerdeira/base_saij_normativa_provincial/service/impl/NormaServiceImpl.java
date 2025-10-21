package com.jcerdeira.base_saij_normativa_provincial.service.impl;

import com.jcerdeira.base_saij_normativa_provincial.entity.Norma;
import com.jcerdeira.base_saij_normativa_provincial.exception.NormaNotFoundException;
import com.jcerdeira.base_saij_normativa_provincial.repository.NormaRepository;
import com.jcerdeira.base_saij_normativa_provincial.service.NormaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NormaServiceImpl implements NormaService {

    private final NormaRepository normaRepository;

    public NormaServiceImpl(NormaRepository normaRepository) {
        this.normaRepository = normaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Norma findNormaById(Long id) {
        return normaRepository.findById(id).orElseThrow(()->new NormaNotFoundException("Norma not found, ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Norma> findAllNormas(){
        return normaRepository.findAll();
    }

    @Override
    @Transactional
    public Norma createNorma(Norma norma) {
        return normaRepository.save(norma);
    }

    @Override
    @Transactional
    public void deleteNormaById(Long id) {
        Norma norma = normaRepository.findById(id).orElseThrow(()->new NormaNotFoundException("Norma not found, ID: " + id));
        normaRepository.deleteById(id);
    }
}
