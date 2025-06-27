/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.LinkedList;
import java.util.Queue;

public class HistorialPeliculas {

    private static final int MAX_HISTORIAL = 10; // Limita la cola a 10 películas (puedes cambiarlo)
    private static Queue<Pelicula> historial = new LinkedList<>();

    // Agrega una película al historial
    public static void agregarAlHistorial(Pelicula pelicula) {
        if (historial.size() == MAX_HISTORIAL) {
            historial.poll(); // elimina el elemento más antiguo (FIFO)
        }
        historial.offer(pelicula);
    }

    // Devuelve el historial actual
    public static Queue<Pelicula> obtenerHistorial() {
        return new LinkedList<>(historial); // devuelve copia para evitar modificaciones externas
    }

    // Limpia el historial (opcional)
    public static void limpiarHistorial() {
        historial.clear();
    }
}
