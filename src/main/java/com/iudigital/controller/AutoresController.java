package com.iudigital.controller;


import com.iudigital.domain.Autores;
import com.iudigital.domain.Libro;
import com.iudigital.repository.AutoresRepository;
import com.iudigital.service.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutoresController {


    @Autowired
    private AutoresService autoresService;



    @PostMapping("/create")
    public ResponseEntity<Autores> createAutores(@RequestBody Autores autores) {
        Autores autorCreado = autoresService.crearAutor(autores);
        return new ResponseEntity<>(autorCreado, HttpStatus.CREATED);
    }

    @GetMapping("/mostrar")
    public ResponseEntity<List<Autores>> listarAutores() {
        List<Autores> autores = autoresService.listarAutores();
        return  ResponseEntity.ok(autores);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable Integer id) {
        try {
            autoresService.eliminarAutor(id);
            System.out.println("ID recibido para eliminar: " + id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Respuesta 204 sin contenido
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Respuesta 404 si no se encuentra
        }
    }




}







