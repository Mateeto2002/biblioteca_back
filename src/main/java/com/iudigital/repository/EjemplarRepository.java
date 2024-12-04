package com.iudigital.repository;

import com.iudigital.domain.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {

    Optional<Ejemplar> findByLibro_IdLibro(Integer idLibro);
}
