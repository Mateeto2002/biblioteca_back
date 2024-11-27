package com.iudigital.config;

import com.iudigital.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Genera una clave secreta segura de 256 bits para HS256
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Método para generar un token JWT
    public String generateToken(Usuario usuario) {

        return Jwts.builder()
                .setSubject(String.valueOf(usuario.getIdUsuarios()))
                .claim("tipoUsuario", usuario.getTipoUsuario())
                .claim("nombre", usuario.getNombre())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Establece la fecha de expiración (1 hora)
                .signWith(SECRET_KEY) // Firma el token con la clave secreta
                .compact(); // Devuelve el token JWT como string
    }

    // Método para validar un token JWT
    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Configura la clave secreta para validar el token
                .build()
                .parseClaimsJws(token) // Parsear el token
                .getBody(); // Extrae los 'claims' (datos) del token
    }

    // Método para obtener el 'subject' (usuario) de un token JWT
    public String getSubjectFromToken(String token) {
        return validateToken(token).getSubject();

    }
}
