/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// archivo: datos/Serie.java
package datos;

public class Serie {
    private String titulo;
    private String rutaImagen;
    private int anio;
    private int duracion;
    private String clasificacion;
    private String idioma;
    private String actores;
    private String resena;
    private String genero;
    private double calificacion;

    public Serie(String titulo, String rutaImagen, int anio, int duracion,
                 String clasificacion, String idioma, String actores,
                 String resena, String genero, double calificacion) {
        this.titulo = titulo;
        this.rutaImagen = rutaImagen;
        this.anio = anio;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.idioma = idioma;
        this.actores = actores;
        this.resena = resena;
        this.genero = genero;
        this.calificacion = calificacion;
    }

    public String getTitulo() { return titulo; }
    public String getRutaImagen() { return rutaImagen; }
    public int getAnio() { return anio; }
    public int getDuracion() { return duracion; }
    public String getClasificacion() { return clasificacion; }
    public String getIdioma() { return idioma; }
    public String getActores() { return actores; }
    public String getResena() { return resena; }
    public String getGenero() { return genero; }
    public double getCalificacion() { return calificacion; }
}