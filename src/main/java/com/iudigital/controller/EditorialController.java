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
    @RequestMapping("/editoriales")
    public class EditorialController {


        @Autowired
        private EditorialService editorialService;


        @PostMapping("/create")
        public ResponseEntity<Editorial> createEditorial(@RequestBody Editorial editorial) {
            Editorial editorialCreado = editorialService.crearEditorial(editorial);
            return new ResponseEntity<>(editorialCreado, HttpStatus.CREATED);
        }

        @GetMapping("/mostrar")
        @CrossOrigin(origins = "http://localhost:3000")
        public ResponseEntity<List<Editorial>> listarEditorial() {
            List<Editorial> editorial = editorialService.listarEditorial();
            return  ResponseEntity.ok(editorial);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> eliminarAutor(@PathVariable Integer id) {
            try {
                editorialService.eliminarEditorial(id);
                System.out.println("ID recibido para eliminar: " + id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Respuesta 204 sin contenido
            } catch (RuntimeException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Respuesta 404 si no se encuentra
            }
        }
    }

