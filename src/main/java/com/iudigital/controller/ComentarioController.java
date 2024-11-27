package com.iudigital.controller;

import com.iudigital.domain.Comentario;
import com.iudigital.repository.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;


    @PostMapping
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario) {
        Comentario comentarioGuardado = comentarioRepository.save(comentario);
        return ResponseEntity.status(201).body(comentarioGuardado);
    }

    @GetMapping()
    public ResponseEntity<List<Comentario>> listarComentarios() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return ResponseEntity.ok(comentarios);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Integer id) {
        return comentarioRepository.findById(id)
                .map(comentario -> {

                    comentario.setComentario(comentario.getComentario());
                    comentario.setFechaComentario(LocalDateTime.now());

                    Comentario comentarioActualizado = comentarioRepository.save(comentario);
                    return ResponseEntity.ok(comentarioActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Integer id) {
        if (!comentarioRepository.existsById(id)) {
            System.out.println("El comentario con la ID " + id + " no existe");
            return ResponseEntity.status(404).build();
        }
        comentarioRepository.deleteById(id);
        System.out.println("El comentario con la ID " + id + " eliminado");
        return ResponseEntity.ok().build();
    }
}

