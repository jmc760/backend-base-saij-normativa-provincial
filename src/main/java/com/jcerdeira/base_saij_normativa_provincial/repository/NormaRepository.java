package com.jcerdeira.base_saij_normativa_provincial.repository;

import com.jcerdeira.base_saij_normativa_provincial.entity.Norma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormaRepository extends JpaRepository<Norma, Long> {
}
