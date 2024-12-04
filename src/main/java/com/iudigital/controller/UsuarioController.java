package com.iudigital.controller;

import com.iudigital.config.JwtUtil;
import com.iudigital.domain.*;
import com.iudigital.repository.EstudianteRepository;
import com.iudigital.repository.ProfesorRepository;
import com.iudigital.repository.UsuarioRepository;
import com.iudigital.service.JwtService;
import com.iudigital.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private JwtService jwtService;

    @Autowired
     private JwtUtil jwtUtil;

    private final UsuarioService usuarioService;
    private final EstudianteRepository estudianteRepository;
    private final ProfesorRepository profesorRepository;



    public UsuarioController(UsuarioService usuarioService, EstudianteRepository estudianteRepository, ProfesorRepository profesorRepository) {
        this.usuarioService = usuarioService;
        this.estudianteRepository = estudianteRepository;
        this.profesorRepository = profesorRepository;
    }



    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Usuario> crearCuenta(@RequestBody RegistroDTO registroDTO) {

        try {

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(registroDTO.getNombre());
            nuevoUsuario.setApellido(registroDTO.getApellido());
            nuevoUsuario.setEmail(registroDTO.getEmail());
            nuevoUsuario.setPassword(registroDTO.getPassword());
            nuevoUsuario.setTipoUsuario(registroDTO.getTipoUsuario());


            Usuario usuarioGuardado = usuarioService.crearCuenta(nuevoUsuario);


            if ("estudiante".equalsIgnoreCase(registroDTO.getTipoUsuario())) {
                Estudiante estudiante = new Estudiante();
                estudiante.setUsuario(usuarioGuardado);
                estudiante.setCurso(registroDTO.getCurso());
                estudiante.setSemestre(registroDTO.getSemestre());
                estudiante.setEdad(registroDTO.getEdad());
                estudianteRepository.save(estudiante);


            } else if ("profesor".equalsIgnoreCase(registroDTO.getTipoUsuario())) {
                Profesor profesor = new Profesor();
                profesor.setUsuario(usuarioGuardado);
                profesor.setEspecializacion(registroDTO.getEspecializacion());
                profesor.setFecha_inicio(registroDTO.getFecha_inicio());
                profesorRepository.save(profesor);
            }


            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }


@PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Usuario usuario_busqueda) {

        try {
            Usuario usuario = usuarioService.iniciarSesion(usuario_busqueda.getEmail(), usuario_busqueda.getPassword());

            // Generar el token
            String token = jwtUtil.generateToken(usuario);

            // Crear la respuesta con token
            return ResponseEntity.ok(new LoginResponse("Inicio de sesión exitoso", token));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de inicio de sesión: " + e.getMessage());
        }
    }

    // Clase para la respuesta
    static class LoginResponse {
        private String mensaje;
        private String token;

        public LoginResponse(String mensaje, String token) {
            this.mensaje = mensaje;
            this.token = token;
        }

        public String getMensaje() {
            return mensaje;
        }

        public String getToken() {
            return token;
        }
    }

    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfil(@RequestHeader("Authorization") String token) {
        try {
            // Validar el token
            Claims claims = jwtService.validateToken(token.replace("Bearer ", ""));
            String email = claims.getSubject();
            String tipoUsuario = claims.get("tipoUsuario", String.class);

            // Retornar los datos del usuario
            return ResponseEntity.ok("Perfil de " + email + " con rol " + tipoUsuario);


        }catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expirado");
        } catch (SignatureException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Firma de token no válida");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }





}





















    /*@PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Usuario usuario_busqueda) {

        Usuario usuario = usuarioService.obtenerPorEmail(usuario_busqueda.getEmail());


        if (usuario == null || !usuario.getPassword().equals(usuario_busqueda.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        // Verificar tipo de usuario
        switch (usuario.getTipoUsuario().toLowerCase()) {
            case "administrador":
                return ResponseEntity.ok("Bienvenido Administrador");


            case "estudiante":
                return ResponseEntity.ok("Bienvenido Estudiante");

            case "profesor":
                return ResponseEntity.ok("Bienvenido Profesor");

            default:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tipo de usuario no permitido");
        }
    }

*/













