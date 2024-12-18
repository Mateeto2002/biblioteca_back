package com.iudigital.service;


import com.iudigital.domain.Autores;
import com.iudigital.domain.Editorial;
import com.iudigital.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {


    @Autowired
private EditorialRepository editorialRepository;


    public Optional<Editorial> findById(Integer id) {
        return editorialRepository.findById(id);
    }

    public Editorial crearEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    public List<Editorial> listarEditorial() {
        return editorialRepository.findAll();
    }

    public void eliminarEditorial(Integer id) {
        Optional<Editorial> editorial = editorialRepository.findById(id);
        if (editorial.isPresent()) {
            editorialRepository.delete(editorial.get());
        } else {
            throw new RuntimeException("Editorial no encontrada");
        }
    }

}
