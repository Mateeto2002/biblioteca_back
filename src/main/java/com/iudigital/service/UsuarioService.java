package com.iudigital.service;



import com.iudigital.domain.Usuario;
import com.iudigital.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearCuenta(Usuario usuario) {

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario iniciarSesion(String email, String contrasena) {

        return usuarioRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(contrasena))
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
    }

    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }


}
