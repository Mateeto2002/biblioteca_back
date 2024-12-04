package com.iudigital.repository;

import com.iudigital.domain.Editorial;
import com.iudigital.domain.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EditorialRepository extends JpaRepository<Editorial, Integer> {



}
