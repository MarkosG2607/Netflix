/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author lenovo
 */
import datos.Pelicula;
import datos.CatalogoPeliculas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaPeliculas extends JFrame {

    private JPanel panelGrid;
    private JTextField txtBuscar;
    private JComboBox<String> cmbOrdenar;
    private List<Pelicula> peliculasOriginal;

    public VentanaPeliculas() {
        setTitle("Películas");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🎨 Panel superior (búsqueda y ordenamiento)
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelTop.setBackground(Color.BLACK);

        txtBuscar = new JTextField(20);
        txtBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
        txtBuscar.setForeground(Color.WHITE);
        txtBuscar.setBackground(new Color(30, 30, 30));
        txtBuscar.setCaretColor(Color.RED);
        txtBuscar.setBorder(BorderFactory.createLineBorder(Color.RED));

        cmbOrdenar = new JComboBox<>(new String[]{
                "Título (A-Z)", "Título (Z-A)", "Año (Recientes)", "Año (Antiguos)"
        });
        cmbOrdenar.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbOrdenar.setForeground(Color.WHITE);
        cmbOrdenar.setBackground(new Color(30, 30, 30));

        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                filtrarYOrdenar();
            }
        });

        cmbOrdenar.addActionListener(e -> filtrarYOrdenar());

        panelTop.add(new JLabel("Buscar: ") {{
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 16));
        }});
        panelTop.add(txtBuscar);
        panelTop.add(Box.createHorizontalStrut(30));
        panelTop.add(new JLabel("Ordenar: ") {{
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 16));
        }});
        panelTop.add(cmbOrdenar);

        add(panelTop, BorderLayout.NORTH);

        // Panel central de galería
        panelGrid = new JPanel(new GridLayout(0, 4, 20, 20));
        panelGrid.setBackground(Color.BLACK);
        panelGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scroll = new JScrollPane(panelGrid);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);

        // Cargar películas
        peliculasOriginal = new ArrayList<>(VentanaAgregarPelicula.catalogo.getPeliculas());
        filtrarYOrdenar();

        setVisible(true);
    }

    private void filtrarYOrdenar() {
        String texto = txtBuscar.getText().toLowerCase();
        List<Pelicula> filtradas = peliculasOriginal.stream()
                .filter(p -> p.getTitulo().toLowerCase().contains(texto))
                .collect(Collectors.toList());

        switch (cmbOrdenar.getSelectedIndex()) {
            case 0: filtradas.sort(Comparator.comparing(Pelicula::getTitulo)); break;
            case 1: filtradas.sort(Comparator.comparing(Pelicula::getTitulo).reversed()); break;
            case 2: filtradas.sort(Comparator.comparing(Pelicula::getAnio).reversed()); break;
            case 3: filtradas.sort(Comparator.comparing(Pelicula::getAnio)); break;
        }

        mostrarPeliculas(filtradas);
    }

    private void mostrarPeliculas(List<Pelicula> lista) {
        panelGrid.removeAll();

        for (Pelicula peli : lista) {
            JPanel panelPeli = new JPanel(new BorderLayout());
            panelPeli.setBackground(Color.BLACK);

            // Imagen con tooltip HTML
            ImageIcon icon = new ImageIcon(peli.getRutaImagen());
            Image img = icon.getImage().getScaledInstance(180, 250, Image.SCALE_SMOOTH);
            JLabel lblImg = new JLabel(new ImageIcon(img));

            // Tooltip personalizado con HTML
            String texto = "<html><body style='width:280px; font-family:Arial'>" +
                    "<b>" + peli.getTitulo() + "</b><br>" +
                    "<span style='color:#e50914; font-weight:bold;'>" + peli.getCalificacion() + "/10</span> " +
                    peli.getDuracion() + "min " + peli.getAnio() + "<br><br>" +
                    peli.getResena() + "<br><br>" +
                    "<b>Género:</b> " + peli.getGenero() + "<br>" +
                    "<b>Actores:</b> " + peli.getActores() +
                    "</body></html>";

            lblImg.setToolTipText(texto);

            // Título
            JLabel lblTitulo = new JLabel(peli.getTitulo(), SwingConstants.CENTER);
            lblTitulo.setForeground(Color.WHITE);
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));

            // Año
            JLabel lblAnio = new JLabel(String.valueOf(peli.getAnio()), SwingConstants.CENTER);
            lblAnio.setFont(new Font("Arial", Font.BOLD, 12));
            lblAnio.setOpaque(true);
            lblAnio.setBackground(new Color(229, 9, 20)); // Rojo Netflix
            lblAnio.setForeground(Color.WHITE);

            // Botón eliminar
            JButton btnEliminar = new JButton("❌ Eliminar");
            btnEliminar.setForeground(Color.WHITE);
            btnEliminar.setBackground(new Color(80, 0, 0));
            btnEliminar.setFocusPainted(false);
            btnEliminar.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar la película \"" + peli.getTitulo() + "\"?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    VentanaAgregarPelicula.catalogo.eliminarPelicula(peli.getTitulo());
                    peliculasOriginal = new ArrayList<>(VentanaAgregarPelicula.catalogo.getPeliculas());
                    filtrarYOrdenar();
                }
            });

            // Panel de info
            JPanel info = new JPanel(new GridLayout(3, 1));
            info.setBackground(Color.BLACK);
            info.add(lblTitulo);
            info.add(lblAnio);
            info.add(btnEliminar);

            panelPeli.add(lblImg, BorderLayout.CENTER);
            panelPeli.add(info, BorderLayout.SOUTH);

            panelGrid.add(panelPeli);
        }

        panelGrid.revalidate();
        panelGrid.repaint();
    }
}


