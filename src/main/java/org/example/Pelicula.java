package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private Integer año;
    private String genero;
    private String director;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opinion> opiniones = new ArrayList<>();

    public Pelicula() {
    }

    public Pelicula(String titulo, Integer año, String genero, String director) {
        this.titulo = titulo;
        this.año = año;
        this.genero = genero;
        this.director = director;
    }

    public void addOpinion(Opinion opinion) {
        opiniones.add(opinion);
        opinion.setPelicula(this);
    }

    public void removeOpinion(Opinion opinion) {
        opiniones.remove(opinion);
        opinion.setPelicula(null);
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", año=" + año +
                ", genero='" + genero + '\'' +
                ", director='" + director + '\'' +
                '}';
    }


    public List<Opinion> getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(List<Opinion> opiniones) {
        this.opiniones = opiniones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
