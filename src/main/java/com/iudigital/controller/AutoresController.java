package com.iudigital.controller;


import com.iudigital.domain.Autores;
import com.iudigital.repository.AutoresRepository;
import com.iudigital.service.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("https://biblioteca-frontend-4e6x.onrender.com")
@RequestMapping("/autores")
public class AutoresController {


    @Autowired
    private AutoresService autoresService;

    @PostMapping
    public ResponseEntity<Autores> createAutores(@RequestBody Autores autores) {
        Autores autorCreado = autoresService.crearAutor(autores);
        return new ResponseEntity<>(autorCreado, HttpStatus.CREATED);
    }




}







