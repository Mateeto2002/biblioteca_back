package com.iudigital.controller;

import com.iudigital.domain.Editorial;
import com.iudigital.domain.Ejemplar;
import com.iudigital.service.EjemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarController {

    @Autowired
    private EjemplarService ejemplarService;


    @GetMapping("/mostrar")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Ejemplar>> listarEjemplar() {
        List<Ejemplar> ejemplar = ejemplarService.listarEjemplar();
        return ResponseEntity.ok(ejemplar);
    }


    // Endpoint para sumar ejemplares
    @PostMapping("/sumar/{idLibro}")
    public ResponseEntity<Ejemplar> sumarEjemplares(@PathVariable Integer idLibro, @RequestParam int cantidad) {
        try {
            Ejemplar ejemplarActualizado = ejemplarService.sumarCantidad(idLibro, cantidad);
            return ResponseEntity.ok(ejemplarActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // La cantidad no es válida
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // No se encontró el ejemplar
        }
    }

    // Endpoint para restar ejemplares
    @PostMapping("/restar/{idLibro}")
    public ResponseEntity<Ejemplar> restarEjemplares(@PathVariable Integer idLibro, @RequestParam int cantidad) {
        try {
            Ejemplar ejemplarActualizado = ejemplarService.restarCantidad(idLibro, cantidad);
            return ResponseEntity.ok(ejemplarActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Cantidad no válida o negativa
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Ejemplar no encontrado
        }
    }
}