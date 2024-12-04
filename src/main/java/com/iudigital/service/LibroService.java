package com.iudigital.service;


import com.iudigital.domain.Autores;
import com.iudigital.domain.Ejemplar;
import com.iudigital.domain.Libro;
import com.iudigital.repository.EjemplarRepository;
import com.iudigital.repository.LibroRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private EjemplarRepository ejemplarRepository;

    public Libro crearLibro(Libro libro) {

        Libro libroGuardado = libroRepository.save(libro);

        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setLibro(libro);
        ejemplar.setCantidad(0);

        ejemplarRepository.save(ejemplar);
        return libroGuardado;
    }

    public void eliminarLibro(Integer id) {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isPresent()) {
            libroRepository.delete(libro.get());
        } else {
            throw new RuntimeException("Libro no encontrado");
        }
    }


    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> findById(Integer id) {
        return libroRepository.findById(id);

    }



}



