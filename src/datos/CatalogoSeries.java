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

public class CatalogoSeries {
    private final ArrayList<Serie> listaSeries;
    private final String archivo = "series.txt";

    public CatalogoSeries() {
        listaSeries = new ArrayList<>();
        cargarDesdeArchivo();
    }

    public boolean agregarSerie(Serie nueva) {
        for (Serie s : listaSeries) {
            if (s.getTitulo().equalsIgnoreCase(nueva.getTitulo())) {
                return false;
            }
        }
        listaSeries.add(nueva);
        guardarEnArchivo();
        return true;
    }

    public ArrayList<Serie> getSeries() {
        return listaSeries;
    }

    public boolean eliminarSerie(String titulo) {
        boolean removido = listaSeries.removeIf(s -> s.getTitulo().equalsIgnoreCase(titulo));
        if (removido) {
            guardarEnArchivo();
        }
        return removido;
    }

    private void guardarEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Serie s : listaSeries) {
                writer.println(String.join(";", s.getTitulo(), s.getRutaImagen(), String.valueOf(s.getAnio()),
                        String.valueOf(s.getDuracion()), s.getClasificacion(), s.getIdioma(),
                        s.getActores(), s.getResena(), s.getGenero(), String.valueOf(s.getCalificacion())));
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
                    Serie s = new Serie(
                            partes[0], partes[1], Integer.parseInt(partes[2]),
                            Integer.parseInt(partes[3]), partes[4], partes[5],
                            partes[6], partes[7], partes[8], Double.parseDouble(partes[9])
                    );
                    listaSeries.add(s);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
