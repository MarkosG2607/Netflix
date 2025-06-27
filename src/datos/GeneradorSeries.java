/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author lenovo
 */
public class GeneradorSeries {

    public static void main(String[] args) {
        CatalogoSeries catalogo = new CatalogoSeries();

        for (int i = 1; i <= 100; i++) {
            String titulo = "Serie " + i;
            String rutaImagen = "imagenes/peli7.jpg"; // Asegúrate de tener esta imagen
            int anio = 2010 + (i % 15); // Años entre 2010 y 2024
            int temporadas = 1 + (i % 7); // Entre 1 y 7 temporadas
            String clasificacion = (i % 2 == 0) ? "TV-MA" : "TV-14";
            String idioma = (i % 3 == 0) ? "Español" : "Inglés";
            String actores = "Actor X, Actor Y, Actor Z";
            String sinopsis = "Sinopsis ficticia de la serie número " + i;
            String genero = (i % 4 == 0) ? "Drama" : (i % 4 == 1) ? "Ciencia Ficción" : (i % 4 == 2) ? "Comedia" : "Terror";
            double calificacion = 1 + (i % 5); // Entre 1 y 5

            Serie serie = new Serie(titulo, rutaImagen, anio, temporadas, clasificacion,
                    idioma, actores, sinopsis, genero, calificacion);

            catalogo.agregarSerie(serie);
        }

        System.out.println("¡100 series generadas con éxito!");
    }
}

