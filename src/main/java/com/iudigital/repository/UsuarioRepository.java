package com.iudigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iudigital.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
