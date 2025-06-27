/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author lenovo
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogoPeliculas {

    private ArrayList<Pelicula> listaPeliculas;
    private final String archivo = "peliculas.txt";

    public CatalogoPeliculas() {
        listaPeliculas = new ArrayList<>();
        cargarDesdeArchivo();
    }

    public boolean agregarPelicula(Pelicula nueva) {
        for (Pelicula p : listaPeliculas) {
            if (p.getTitulo().equalsIgnoreCase(nueva.getTitulo())) {
                return false;
            }
        }
        listaPeliculas.add(nueva);
        guardarEnArchivo();
        return true;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return listaPeliculas;
    }

    public boolean eliminarPelicula(String titulo) {
        boolean removido = listaPeliculas.removeIf(p -> p.getTitulo().equalsIgnoreCase(titulo));
        if (removido) {
            guardarEnArchivo();
        }
        return removido;
    }

    private void guardarEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Pelicula p : listaPeliculas) {
                writer.println(String.join(";", 
                    p.getTitulo(), 
                    p.getRutaImagen(), 
                    String.valueOf(p.getAnio()),
                    String.valueOf(p.getDuracion()), 
                    p.getClasificacion(), 
                    p.getIdioma(),
                    p.getActores(), 
                    p.getResena(), 
                    p.getGenero(), 
                    String.valueOf(p.getCalificacion())
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDesdeArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 10) {
                    Pelicula p = new Pelicula(
                        partes[0], // titulo
                        partes[1], // ruta imagen
                        Integer.parseInt(partes[2]), // año
                        Integer.parseInt(partes[3]), // duracion
                        partes[4], // clasificacion
                        partes[5], // idioma
                        partes[6], // actores
                        partes[7], // reseña
                        partes[8], // genero
                        Double.parseDouble(partes[9]) // calificación
                    );
                    listaPeliculas.add(p);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // MÉTODOS DE ORDENAMIENTO Y FILTRADO

    // Ordenar por título ascendente
    public List<Pelicula> ordenarPorTituloAscendente() {
        return listaPeliculas.stream()
                .sorted(Comparator.comparing(Pelicula::getTitulo))
                .collect(Collectors.toList());
    }

    // Ordenar por título descendente
    public List<Pelicula> ordenarPorTituloDescendente() {
        return listaPeliculas.stream()
                .sorted(Comparator.comparing(Pelicula::getTitulo).reversed())
                .collect(Collectors.toList());
    }

    // Ordenar por año ascendente
    public List<Pelicula> ordenarPorAnioAscendente() {
        return listaPeliculas.stream()
                .sorted(Comparator.comparingInt(Pelicula::getAnio))
                .collect(Collectors.toList());
    }

    // Ordenar por año descendente
    public List<Pelicula> ordenarPorAnioDescendente() {
        return listaPeliculas.stream()
                .sorted(Comparator.comparingInt(Pelicula::getAnio).reversed())
                .collect(Collectors.toList());
    }

    // Filtrar por género
    public List<Pelicula> filtrarPorGenero(String genero) {
        return listaPeliculas.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    // Filtrar por clasificación
    public List<Pelicula> filtrarPorClasificacion(String clasificacion) {
        return listaPeliculas.stream()
                .filter(p -> p.getClasificacion().equalsIgnoreCase(clasificacion))
                .collect(Collectors.toList());
    }

    // Filtrar por letra inicial del título
    public List<Pelicula> filtrarPorLetraInicial(char letra) {
        return listaPeliculas.stream()
                .filter(p -> p.getTitulo().toLowerCase().startsWith(String.valueOf(letra).toLowerCase()))
                .collect(Collectors.toList());
    }
}
