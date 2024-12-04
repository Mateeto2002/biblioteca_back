package com.iudigital.repository;

import com.iudigital.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    List<Prestamo> findByUsuario_IdUsuarios(Integer idUsuario);
    List<Prestamo> findByEjemplar_IdEjemplar(Integer idEjemplar);
}
