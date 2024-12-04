package com.iudigital.service;

import com.iudigital.domain.Editorial;
import com.iudigital.domain.Ejemplar;
import com.iudigital.repository.EjemplarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EjemplarService {

    @Autowired
    private EjemplarRepository ejemplarRepository;

    @Transactional
    protected Ejemplar actualizarCantidad(Integer idLibro, int cambioCantidad) {
        Ejemplar ejemplar = ejemplarRepository.findByLibro_IdLibro(idLibro)
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado para el libro con ID " + idLibro));

        int nuevaCantidad = ejemplar.getCantidad() + cambioCantidad;

        // Validar que no sea negativa
        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException("La cantidad de ejemplares no puede ser negativa para el libro con ID " + idLibro);
        }

        ejemplar.setCantidad(nuevaCantidad);
        return ejemplarRepository.save(ejemplar);
    }

    // Método para sumar ejemplares
    @Transactional
    public Ejemplar sumarCantidad(Integer idLibro, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a sumar debe ser mayor que cero.");
        }
        return actualizarCantidad(idLibro, cantidad);
    }

    // Método para restar ejemplares
    @Transactional
    public Ejemplar restarCantidad(Integer idLibro, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a restar debe ser mayor que cero.");
        }
        return actualizarCantidad(idLibro, -cantidad);
    }

    public List<Ejemplar> listarEjemplar() {
        return ejemplarRepository.findAll();
    }




    }

