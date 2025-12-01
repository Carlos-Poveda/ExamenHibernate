package org.example;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {

        SessionFactory factory = DataProvider.getSessionFactory();
        DataService dataService = new DataService(factory);

        // SavePelicula
        System.out.println("---SAVE---");

        Pelicula pelicula = new Pelicula("Ejemplo Pelicula",2025,"Drama","Yo");

        System.out.println(dataService.savePelicula(pelicula));

        // FindOpinionByCorreo
        System.out.println("---BUSCAR OPINIONES POR CORREO---");

        List<Opinion> opinionesDeUsuarioX = new ArrayList<>();
        opinionesDeUsuarioX = dataService.findOpinionesByUsuario("user1@example.com");
        for (Opinion opinion : opinionesDeUsuarioX) {
            System.out.println(opinion);
        }

        // Añadir opinión a película
        System.out.println("---AÑADIR UNA OPINIÓN A PELÍCULA---");

        Opinion nuevaOpinion = new Opinion();
        nuevaOpinion.setDescripcion("Ejemplo de opinión.");
        nuevaOpinion.setUsuario("ejemplo@ejemplo.com");
        nuevaOpinion.setPuntuacion(10);

        dataService.agregarOpinion(2, nuevaOpinion);

        // Find Peliculas By Puntuación
        System.out.println("---BUSCAR PELÍCULAS POR PUNTUACIÓN---");

        List<Pelicula> peliculasMenosDe3 = dataService.findPeliculasByMinPuntuacion(3);

        for (Pelicula peli : peliculasMenosDe3) {
            System.out.println(peli);
        }
    }
}
