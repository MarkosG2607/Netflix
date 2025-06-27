/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import datos.CatalogoPeliculas;
import datos.HistorialPeliculas;  // Importar historial
import datos.Pelicula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class InterfazSexflix extends JFrame {

    private boolean esAdmin;
    private CatalogoPeliculas catalogo;
    private Pelicula peliculaDestacada;
    private JLabel tituloDestacado;
    private JLabel lblImagenDestacada;
    private final Random rand = new Random();

    public InterfazSexflix(boolean esAdmin) {
        this.esAdmin = esAdmin;

        setTitle("Sexflix");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Barra superior estilo Netflix
        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraSuperior.setBackground(Color.BLACK);

        JLabel logo = new JLabel("SEXFLIX");
        logo.setForeground(Color.RED);
        logo.setFont(new Font("Arial", Font.BOLD, 28));
        barraSuperior.add(logo);

        String[] menuItems = {
            "Inicio", "Series", "Películas", "Historial"  // <-- Nuevo botón
        };

        for (String item : menuItems) {
            JLabel label = new JLabel(item);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.PLAIN, 16));
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    switch (item) {
                        case "Inicio":
                            cambiarPeliculaDestacada();
                            break;
                        case "Series":
                            new VentanaSeries().setVisible(true);
                            break;
                        case "Películas":
                            new VentanaPeliculas().setVisible(true);
                            break;
                        case "Historial":
                            mostrarHistorial();
                            break;
                    }
                }
            });

            barraSuperior.add(Box.createHorizontalStrut(15));
            barraSuperior.add(label);
        }

        add(barraSuperior, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(new Color(40, 40, 40));

        catalogo = new CatalogoPeliculas();

        // Imagen destacada
        lblImagenDestacada = new JLabel("", SwingConstants.CENTER);
        panelCentral.add(lblImagenDestacada, BorderLayout.NORTH);

        // Título destacado
        tituloDestacado = new JLabel("🎬", SwingConstants.CENTER);
        tituloDestacado.setForeground(Color.WHITE);
        tituloDestacado.setFont(new Font("Arial", Font.BOLD, 30));
        panelCentral.add(tituloDestacado, BorderLayout.CENTER);

        // Panel botones
        JPanel botones = new JPanel();
        botones.setBackground(new Color(40, 40, 40));

        JButton btnInfo = new JButton("Más info");
        estilizarBotonNetflix(btnInfo);
        botones.add(btnInfo);

        if (esAdmin) {
            JButton btnAgregar = new JButton("Agregar Película");
            estilizarBotonNetflix(btnAgregar);
            btnAgregar.addActionListener(e -> new VentanaAgregarPelicula().setVisible(true));

            JButton btnAgregar1 = new JButton("Agregar Serie");
            estilizarBotonNetflix(btnAgregar1);
            btnAgregar1.addActionListener(e -> new VentanaAgregarSerie().setVisible(true));

            JButton btnAdmin = new JButton("Modificar/Eliminar Películas");
            estilizarBotonNetflix(btnAdmin);
            btnAdmin.addActionListener(e -> new VentanaModificarEliminarPelicula().setVisible(true));

            botones.add(btnAgregar);
            botones.add(btnAgregar1);
            botones.add(btnAdmin);
        }

        panelCentral.add(botones, BorderLayout.SOUTH);
        add(panelCentral, BorderLayout.CENTER);

        btnInfo.addActionListener(e -> {
            if (peliculaDestacada != null) {
                String info = String.format(
                        "🎬 %s\n📅 Año: %d\n🕒 Duración: %d min\n📊 Calificación: %.1f\n🔞 Clasificación: %s\n🎭 Género: %s\n🗣 Idioma: %s\n🎭 Actores: %s\n📝 Reseña: %s",
                        peliculaDestacada.getTitulo(),
                        peliculaDestacada.getAnio(),
                        peliculaDestacada.getDuracion(),
                        peliculaDestacada.getCalificacion(),
                        peliculaDestacada.getClasificacion(),
                        peliculaDestacada.getGenero(),
                        peliculaDestacada.getIdioma(),
                        peliculaDestacada.getActores(),
                        peliculaDestacada.getResena()
                );
                JOptionPane.showMessageDialog(this, info, "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No hay películas disponibles.");
            }
        });

        Timer timer = new Timer(10000, (ActionEvent e) -> cambiarPeliculaDestacada());
        timer.start();

        cambiarPeliculaDestacada();

        setVisible(true);
    }

    private void cambiarPeliculaDestacada() {
        if (!catalogo.getPeliculas().isEmpty()) {
            peliculaDestacada = catalogo.getPeliculas().get(rand.nextInt(catalogo.getPeliculas().size()));
            tituloDestacado.setText(peliculaDestacada.getTitulo());

            String ruta = peliculaDestacada.getRutaImagen();
            if (ruta != null && !ruta.isEmpty()) {
                ImageIcon iconoOriginal = new ImageIcon(ruta);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(250, 360, Image.SCALE_SMOOTH);
                lblImagenDestacada.setIcon(new ImageIcon(imagenEscalada));
            } else {
                lblImagenDestacada.setIcon(null);
            }

            // Agregar la película destacada al historial
            datos.HistorialPeliculas.agregarAlHistorial(peliculaDestacada);
        } else {
            tituloDestacado.setText("No hay películas registradas");
            lblImagenDestacada.setIcon(null);
        }
    }

    private void mostrarHistorial() {
        java.util.Queue<Pelicula> historial = datos.HistorialPeliculas.obtenerHistorial();

        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay películas en el historial.");
            return;
        }

        StringBuilder sb = new StringBuilder("Historial de películas vistas:\n\n");
        for (Pelicula p : historial) {
            sb.append("- ").append(p.getTitulo()).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setBackground(new Color(18, 18, 18));
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Historial", JOptionPane.INFORMATION_MESSAGE);
    }

    private void estilizarBotonNetflix(JButton boton) {
        boton.setBackground(new Color(229, 9, 20));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
