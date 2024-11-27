package com.iudigital.controller;

import com.iudigital.domain.Autores;
import com.iudigital.domain.Editorial;
import com.iudigital.domain.Libro;
import com.iudigital.domain.LibroDTO;
import com.iudigital.repository.AutoresRepository;
import com.iudigital.repository.EditorialRepository;
import com.iudigital.service.AutoresService;
import com.iudigital.service.EditorialService;
import com.iudigital.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LibroController {

    @Autowired
    private  EditorialService editorialService;
    @Autowired
    private  AutoresService autoresService;
    @Autowired
    private  LibroService libroService;

    @PostMapping("/libro")
    public ResponseEntity<Libro> crearLibro(@RequestBody LibroDTO libroDTO) {


        try {
            Libro libroCrear = new Libro();

            libroCrear.setTitulo(libroDTO.getTitulo());
            libroCrear.setCategoria(libroDTO.getCategoria_1());
            libroCrear.setCategoria2(libroDTO.getCategoria_2());
            libroCrear.setImg(libroDTO.getImg());

            Editorial editorial = editorialService.findById(libroDTO.getEditorial())
                    .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));
            libroCrear.setEditorial(editorial);

            Autores autor = autoresService.findById(libroDTO.getAutor())
                    .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
            libroCrear.setAutores(autor);

            Libro libroGuardado = libroService.crearLibro(libroCrear);
            System.out.println(libroDTO);



            return ResponseEntity.status(HttpStatus.CREATED).body(libroGuardado);

        }catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/libro")
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> libros = libroService.listarLibros();
        return  ResponseEntity.ok(libros);
    }




}
