package com.iudigital.controller;
import com.iudigital.domain.Prestamo;
import com.iudigital.domain.PrestamoDTO;
import com.iudigital.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;



@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private  PrestamoService prestamoService;



    @PostMapping("/create")
    public ResponseEntity<Prestamo> prestarLibro(@RequestBody PrestamoDTO request) {

        if (request.getIdUsuario() == null || request.getIdEjemplar() == null || request.getFechaFinalizacion() == null) {
            return ResponseEntity.badRequest().build(); // Responder con un error si los datos no son válidos
        }

        Prestamo prestamo = prestamoService.crearPrestamo(
                request.getIdUsuario(),
                request.getIdEjemplar(),
                request.getFechaFinalizacion()
        );
        return ResponseEntity.ok(prestamo);
    }

    @GetMapping("/mostrar")
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        List<Prestamo> prestamos = prestamoService.listaPrestamos();
        return ResponseEntity.ok(prestamos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Integer id) {
        try {

            prestamoService.devolverPrestamo(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




}
