package com.iudigital.controller;


import com.iudigital.domain.Autores;
import com.iudigital.domain.Editorial;
import com.iudigital.repository.EditorialRepository;
import com.iudigital.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


    @RestController
    @RequestMapping("/editorial")
    public class EditorialController {


        @Autowired
        private EditorialService editorialService;


        @PostMapping
        public ResponseEntity<Editorial> createEditorial(@RequestBody Editorial editorial) {
            Editorial editorialCreado = editorialService.crearEditorial(editorial);
            return new ResponseEntity<>(editorialCreado, HttpStatus.CREATED);
        }
    }

