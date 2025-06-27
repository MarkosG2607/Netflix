/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author lenovo
 */
public class GeneradorPeliculas {

    public static void main(String[] args) {
        CatalogoPeliculas catalogo = new CatalogoPeliculas();

        for (int i = 1; i <= 100; i++) {
            String titulo = "Película " + i;
            String rutaImagen = "imagenes/peli1.jpg"; // Usa una imagen que sí exista
            int anio = 2000 + (i % 24); // Año entre 2000 y 2023
            int duracion = 90 + (i % 60); // Duración entre 90 y 150 minutos
            String clasificacion = (i % 2 == 0) ? "PG-13" : "R";
            String idioma = (i % 3 == 0) ? "Español" : "Inglés";
            String actores = "Actor A, Actor B, Actor C";
            String resena = "Esta es una reseña ficticia de la película número " + i;
            String genero = (i % 4 == 0) ? "Acción" : (i % 4 == 1) ? "Comedia" : (i % 4 == 2) ? "Terror" : "Drama";
            double calificacion = 1 + (i % 5); // Entre 1 y 5

            Pelicula p = new Pelicula(titulo, rutaImagen, anio, duracion, clasificacion,
                    idioma, actores, resena, genero, calificacion);

            catalogo.agregarPelicula(p);
        }

        System.out.println("¡100 películas generadas con éxito!");
    }
}

