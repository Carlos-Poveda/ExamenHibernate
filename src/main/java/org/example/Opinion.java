package org.example;

import jakarta.persistence.*;

@Entity
@Table(name="opinion")
public class Opinion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private String usuario;
    private Integer puntuacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;


    public Opinion() {
    }

    public Opinion(Integer id, String descripcion, String usuario, Integer puntuacion, Pelicula pelicula) {
        this.id = id;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", puntuacion=" + puntuacion +
//                ", pelicula=" + pelicula.getTitulo() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
