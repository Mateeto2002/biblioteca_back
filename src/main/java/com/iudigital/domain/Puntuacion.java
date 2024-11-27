package com.iudigital.domain;
import jakarta.persistence.*;


@Entity
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPuntuacion;

    private Integer valoracion;

    @ManyToOne
    @JoinColumn(name = "ID_Usuarios", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Libro", nullable = false)
    private Libro libro;

    public Integer getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(Integer idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
