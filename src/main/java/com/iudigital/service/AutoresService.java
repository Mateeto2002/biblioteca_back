package com.iudigital.service;


import com.iudigital.domain.Autores;
import com.iudigital.repository.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
