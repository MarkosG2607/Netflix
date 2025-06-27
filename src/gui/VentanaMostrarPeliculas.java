/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import datos.CatalogoPeliculas;
import datos.HistorialPeliculas;
import datos.Pelicula;
import datos.VentanaDetallePelicula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Queue;

public class VentanaMostrarPeliculas extends JFrame {

    private JPanel panelGrid;
    private CatalogoPeliculas catalogo;

    private JComboBox<String> comboOrden;
    private JComboBox<String> comboFiltro;
    private JTextField txtLetra;
    private JButton btnVerHistorial;  // Nuevo botón para historial

    public VentanaMostrarPeliculas() {
        setTitle("Catálogo de Películas");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        catalogo = new CatalogoPeliculas();

        // Panel de controles
        JPanel panelControles = new JPanel(new FlowLayout());
        panelControles.setBackground(Color.BLACK);

        comboOrden = new JComboBox<>(new String[]{
                "Ordenar por Título (A-Z)",
                "Ordenar por Título (Z-A)",
                "Ordenar por Año (Asc)",
                "Ordenar por Año (Desc)"
        });

        comboFiltro = new JComboBox<>(new String[]{
                "Sin filtro",
                "Filtrar por Género: Acción",
                "Filtrar por Género: Comedia",
                "Filtrar por Clasificación: PG-13",
                "Filtrar por Clasificación: R"
        });

        txtLetra = new JTextField(2);
        txtLetra.setToolTipText("Letra inicial");

        JButton btnAplicar = new JButton("Aplicar");
        btnAplicar.addActionListener(e -> actualizarPeliculas());

        btnVerHistorial = new JButton("Ver Historial");  // Crear botón historial
        btnVerHistorial.addActionListener(e -> mostrarHistorial());

        panelControles.add(comboOrden);
        panelControles.add(comboFiltro);
        panelControles.add(new JLabel("Letra:"));
        panelControles.add(txtLetra);
        panelControles.add(btnAplicar);
        panelControles.add(btnVerHistorial);  // Añadir botón al panel

        add(panelControles, BorderLayout.NORTH);

        // Panel de películas (cuadros)
        panelGrid = new JPanel(new GridLayout(0, 4, 20, 20));
        panelGrid.setBackground(new Color(10, 10, 25));
        panelGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scroll = new JScrollPane(panelGrid);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        // Mostrar inicial
        actualizarPeliculas();

        setVisible(true);
    }

    private void actualizarPeliculas() {
        panelGrid.removeAll();

        List<Pelicula> lista = catalogo.getPeliculas();

        // Aplicar orden
        switch (comboOrden.getSelectedIndex()) {
            case 0 -> lista = catalogo.ordenarPorTituloAscendente();
            case 1 -> lista = catalogo.ordenarPorTituloDescendente();
            case 2 -> lista = catalogo.ordenarPorAnioAscendente();
            case 3 -> lista = catalogo.ordenarPorAnioDescendente();
        }

        // Aplicar filtro
        switch (comboFiltro.getSelectedIndex()) {
            case 1 -> lista = catalogo.filtrarPorGenero("Acción");
            case 2 -> lista = catalogo.filtrarPorGenero("Comedia");
            case 3 -> lista = catalogo.filtrarPorClasificacion("PG-13");
            case 4 -> lista = catalogo.filtrarPorClasificacion("R");
        }

        // Filtro por letra inicial
        String letra = txtLetra.getText().trim();
        if (!letra.isEmpty() && letra.length() == 1) {
            lista = catalogo.filtrarPorLetraInicial(letra.charAt(0));
        }

        for (Pelicula peli : lista) {
            JPanel panelPeli = new JPanel(new BorderLayout());
            panelPeli.setBackground(new Color(10, 10, 25));

            // Imagen
            ImageIcon icon = new ImageIcon(peli.getRutaImagen());
            Image img = icon.getImage().getScaledInstance(180, 250, Image.SCALE_SMOOTH);
            JLabel lblImg = new JLabel(new ImageIcon(img));
            lblImg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Al hacer clic, agregar a historial
                    HistorialPeliculas.agregarAlHistorial(peli);
                    // Mostrar detalle
                    new VentanaDetallePelicula(peli);
                }
            });

            // Título
            JLabel lblTitulo = new JLabel(peli.getTitulo(), SwingConstants.CENTER);
            lblTitulo.setForeground(Color.WHITE);

            // Año
            JLabel lblAnio = new JLabel(String.valueOf(peli.getAnio()), SwingConstants.CENTER);
            lblAnio.setForeground(Color.WHITE);
            lblAnio.setFont(new Font("Arial", Font.BOLD, 12));
            lblAnio.setOpaque(true);
            lblAnio.setBackground(new Color(229, 9, 20)); // Estilo Netflix

            JPanel info = new JPanel(new BorderLayout());
            info.setBackground(new Color(10, 10, 25));
            info.add(lblTitulo, BorderLayout.CENTER);
            info.add(lblAnio, BorderLayout.SOUTH);

            panelPeli.add(lblImg, BorderLayout.CENTER);
            panelPeli.add(info, BorderLayout.SOUTH);

            panelGrid.add(panelPeli);
        }

        panelGrid.revalidate();
        panelGrid.repaint();
    }

    private void mostrarHistorial() {
        Queue<Pelicula> historial = HistorialPeliculas.obtenerHistorial();

        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay películas en el historial.");
            return;
        }

        // Crear texto para mostrar la lista de títulos en el historial
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
}
