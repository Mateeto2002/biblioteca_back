package com.iudigital.service;

import com.iudigital.domain.Ejemplar;
import com.iudigital.domain.Prestamo;
import com.iudigital.domain.Usuario;
import com.iudigital.repository.EjemplarRepository;
import com.iudigital.repository.PrestamoRepository;
import com.iudigital.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private EjemplarService ejemplarService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Prestamo crearPrestamo(Integer idUsuarios, Integer idEjemplar, Date fechaFinalizacion) {

        Ejemplar ejemplar = ejemplarService.restarCantidad(idEjemplar, 1);

        Usuario usuario = usuarioRepository.findById(idUsuarios)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID " + idUsuarios));

            Prestamo prestamo = new Prestamo();

            prestamo.setEjemplar(ejemplar);
            prestamo.setFechaInicio(new Date());
            prestamo.setFechaFinalizacion(fechaFinalizacion);
            prestamo.setUsuario(usuario);

            return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> listaPrestamos() {
        return prestamoRepository.findAll();
    }

    public void devolverPrestamo(Integer idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        // Sumar un ejemplar al devolver el préstamo
        ejemplarService.sumarCantidad(prestamo.getEjemplar().getIdEjemplar(), 1);

        // Eliminar el préstamo (o marcarlo como devuelto)
        prestamoRepository.delete(prestamo);
    }

}
