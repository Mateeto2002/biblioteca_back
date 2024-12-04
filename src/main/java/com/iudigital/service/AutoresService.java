package com.iudigital.service;


import com.iudigital.domain.Autores;
import com.iudigital.domain.Libro;
import com.iudigital.repository.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoresService {


    @Autowired
    private AutoresRepository autoresRepository;


    public Optional<Autores> findById(Integer id) {
        return autoresRepository.findById(id);
    }

    public Autores crearAutor(Autores autores){
        return autoresRepository.save(autores);
    }

    public List<Autores> listarAutores() {
        return autoresRepository.findAll();
    }


    public void eliminarAutor(Integer id) {
        Optional<Autores> autores = autoresRepository.findById(id);
        if (autores.isPresent()) {
            autoresRepository.delete(autores.get());
        } else {
            throw new RuntimeException("Autor no encontrado");
        }

    }



}
