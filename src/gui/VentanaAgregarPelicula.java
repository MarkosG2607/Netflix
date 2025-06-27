/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import datos.CatalogoPeliculas;
import datos.Pelicula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class VentanaAgregarPelicula extends JFrame {

    private JTextField txtTitulo, txtAnio, txtCalificacion, txtDuracion,
            txtClasificacion, txtIdioma, txtActores, txtGenero;
    private JTextArea txtResena;
    private JLabel lblRutaImagen;
    private String rutaImagen = "";

    public static CatalogoPeliculas catalogo = new CatalogoPeliculas();

    public VentanaAgregarPelicula() {
        setTitle("Agregar Película");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(18, 18, 18));  // fondo oscuro estilo Netflix

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;

        // Método para agregar etiquetas estilizadas
        JLabel lblTitulo = crearEtiqueta("Título:");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblTitulo, gbc);
        txtTitulo = crearCampoTexto();
        gbc.gridx = 1; gbc.gridy = y++;
        add(txtTitulo, gbc);

        JLabel lblAnio = crearEtiqueta("Año:");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblAnio, gbc);
        txtAnio = crearCampoTexto();
        gbc.gridx = 1; gbc.gridy = y++;
        add(txtAnio, gbc);

        JLabel lblDuracion = crearEtiqueta("Duración (min):");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblDuracion, gbc);
        txtDuracion = crearCampoTexto();
        gbc.gridx = 1; gbc.gridy = y++;
        add(txtDuracion, gbc);

        JLabel lblClasificacion = crearEtiqueta("Clasificación:");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblClasificacion, gbc);
        txtClasificacion = crearCampoTexto();
        gbc.gridx = 1; gbc.gridy = y++;
        add(txtClasificacion, gbc);

        JLabel lblIdioma = crearEtiqueta("Idioma:");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblIdioma, gbc);
        txtIdioma = crearCampoTexto();
        gbc.gridx = 1; gbc.gridy = y++;
        add(txtIdioma, gbc);

        JLabel lblActores = crearEtiqueta("Actores:");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblActores, gbc);
        txtActores = crearCampoTexto();
        gbc.gridx = 1; gbc.gridy = y++;
        add(txtActores, gbc);

        JLabel lblCalificacion = crearEtiqueta("Calificación (1-10):");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblCalificacion, gbc);
        txtCalificacion = crearCampoTexto();
        gbc.gridx = 1; gbc.gridy = y++;
        add(txtCalificacion, gbc);

        JLabel lblGenero = crearEtiqueta("Género:");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblGenero, gbc);
        txtGenero = crearCampoTexto();
        gbc.gridx = 1; gbc.gridy = y++;
        add(txtGenero, gbc);

        JLabel lblResena = crearEtiqueta("Reseña:");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblResena, gbc);
        txtResena = new JTextArea(4, 20);
        txtResena.setLineWrap(true);
        txtResena.setWrapStyleWord(true);
        txtResena.setFont(new Font("Arial", Font.PLAIN, 14));
        txtResena.setBackground(new Color(30, 30, 30));
        txtResena.setForeground(Color.WHITE);
        JScrollPane scrollResena = new JScrollPane(txtResena);
        gbc.gridx = 1; gbc.gridy = y++;
        add(scrollResena, gbc);

        // Imagen
        JLabel lblImagen = crearEtiqueta("Imagen (portada):");
        gbc.gridx = 0; gbc.gridy = y;
        add(lblImagen, gbc);

        JPanel panelImg = new JPanel(new BorderLayout(5, 0));
        panelImg.setBackground(new Color(18, 18, 18));
        lblRutaImagen = new JLabel("Sin imagen");
        lblRutaImagen.setForeground(Color.LIGHT_GRAY);
        lblRutaImagen.setFont(new Font("Arial", Font.ITALIC, 13));

        JButton btnImagen = crearBotonNetflixPequeno("Seleccionar imagen");
        btnImagen.addActionListener(this::seleccionarImagen);

        panelImg.add(lblRutaImagen, BorderLayout.CENTER);
        panelImg.add(btnImagen, BorderLayout.EAST);

        gbc.gridx = 1; gbc.gridy = y++;
        add(panelImg, gbc);

        // Botón guardar
        JButton btnGuardar = crearBotonNetflixGrande("Guardar Película");
        btnGuardar.addActionListener(this::guardarPelicula);
        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        setVisible(true);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }

    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField(20);
        campo.setBackground(new Color(30, 30, 30));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createLineBorder(new Color(229, 9, 20), 2));
        return campo;
    }

    private JButton crearBotonNetflixPequeno(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(229, 9, 20));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        return boton;
    }

    private JButton crearBotonNetflixGrande(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(229, 9, 20));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        return boton;
    }

    private void seleccionarImagen(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            rutaImagen = archivo.getAbsolutePath();
            lblRutaImagen.setText(archivo.getName());
            lblRutaImagen.setForeground(Color.WHITE);
            lblRutaImagen.setFont(new Font("Arial", Font.PLAIN, 14));
        }
    }

    private void guardarPelicula(ActionEvent e) {
        if (txtTitulo.getText().isEmpty() || txtAnio.getText().isEmpty() || txtDuracion.getText().isEmpty()
                || txtClasificacion.getText().isEmpty() || txtIdioma.getText().isEmpty() || txtActores.getText().isEmpty()
                || txtCalificacion.getText().isEmpty() || txtGenero.getText().isEmpty()
                || txtResena.getText().isEmpty() || rutaImagen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor llena todos los campos.");
            return;
        }

        try {
            Pelicula peli = new Pelicula(
                    txtTitulo.getText(),
                    rutaImagen,
                    Integer.parseInt(txtAnio.getText()),
                    Integer.parseInt(txtDuracion.getText()),
                    txtClasificacion.getText(),
                    txtIdioma.getText(),
                    txtActores.getText(),
                    txtResena.getText(),
                    txtGenero.getText(),
                    Double.parseDouble(txtCalificacion.getText())
            );

            if (catalogo.agregarPelicula(peli)) {
                JOptionPane.showMessageDialog(this, "Película agregada correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe una película con ese título.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Año, duración y calificación deben ser numéricos.");
        }
    }
}
