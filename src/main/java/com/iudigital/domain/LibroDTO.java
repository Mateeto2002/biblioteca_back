package com.iudigital.domain;

public class LibroDTO {

    private String titulo;
    private String categoria_1;
    private String categoria_2;
    private String img;


    private Integer autor;
    private Integer editorial;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria_1() {
        return categoria_1;
    }

    public void setCategoria_1(String categoria_1) {
        this.categoria_1 = categoria_1;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategoria_2() {
        return categoria_2;
    }

    public void setCategoria_2(String categoria_2) {
        this.categoria_2 = categoria_2;
    }

    public Integer getAutor() {
        return autor;
    }

    public void setAutor(Integer autor) {
        this.autor = autor;
    }

    public Integer getEditorial() {
        return editorial;
    }

    public void setEditorial(Integer editorial) {
        this.editorial = editorial;
    }
}
