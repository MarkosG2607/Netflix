/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author lenovo
 */
import datos.CatalogoSeries;
import datos.Serie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class VentanaAgregarSerie extends JFrame {

    private JTextField txtTitulo, txtAnio, txtCalificacion, txtDuracion,
            txtClasificacion, txtIdioma, txtActores, txtGenero;
    private JTextArea txtResena;
    private JLabel lblRutaImagen;
    private String rutaImagen = "";

    public static CatalogoSeries catalogo = new CatalogoSeries();

    public VentanaAgregarSerie() {
        setTitle("Agregar Serie");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(18, 18, 18)); // fondo negro estilo Netflix

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;

        // Agregar campos con estilo
        addEtiqueta("Título:", gbc, 0, y);
        txtTitulo = crearCampoTexto();
        addCampoTexto(txtTitulo, gbc, 1, y++);

        addEtiqueta("Año:", gbc, 0, y);
        txtAnio = crearCampoTexto();
        addCampoTexto(txtAnio, gbc, 1, y++);

        addEtiqueta("Duración (min):", gbc, 0, y);
        txtDuracion = crearCampoTexto();
        addCampoTexto(txtDuracion, gbc, 1, y++);

        addEtiqueta("Clasificación:", gbc, 0, y);
        txtClasificacion = crearCampoTexto();
        addCampoTexto(txtClasificacion, gbc, 1, y++);

        addEtiqueta("Idioma:", gbc, 0, y);
        txtIdioma = crearCampoTexto();
        addCampoTexto(txtIdioma, gbc, 1, y++);

        addEtiqueta("Actores:", gbc, 0, y);
        txtActores = crearCampoTexto();
        addCampoTexto(txtActores, gbc, 1, y++);

        addEtiqueta("Calificación (1-10):", gbc, 0, y);
        txtCalificacion = crearCampoTexto();
        addCampoTexto(txtCalificacion, gbc, 1, y++);

        addEtiqueta("Género:", gbc, 0, y);
        txtGenero = crearCampoTexto();
        addCampoTexto(txtGenero, gbc, 1, y++);

        addEtiqueta("Reseña:", gbc, 0, y);
        txtResena = new JTextArea(4, 20);
        txtResena.setLineWrap(true);
        txtResena.setWrapStyleWord(true);
        txtResena.setFont(new Font("Arial", Font.PLAIN, 14));
        txtResena.setBackground(new Color(30, 30, 30));
        txtResena.setForeground(Color.WHITE);
        JScrollPane scrollResena = new JScrollPane(txtResena);
        gbc.gridx = 1; gbc.gridy = y++;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollResena, gbc);
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Imagen
        addEtiqueta("Imagen (portada):", gbc, 0, y);
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
        JButton btnGuardar = crearBotonNetflixGrande("Guardar Serie");
        btnGuardar.addActionListener(this::guardarSerie);
        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        setVisible(true);
    }

    private void addEtiqueta(String texto, GridBagConstraints gbc, int x, int y) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = x;
        gbc.gridy = y;
        add(label, gbc);
    }

    private void addCampoTexto(JTextField campo, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(campo, gbc);
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

    private void guardarSerie(ActionEvent e) {
        if (txtTitulo.getText().isEmpty() || txtAnio.getText().isEmpty() || txtDuracion.getText().isEmpty()
                || txtClasificacion.getText().isEmpty() || txtIdioma.getText().isEmpty() || txtActores.getText().isEmpty()
                || txtCalificacion.getText().isEmpty() || txtGenero.getText().isEmpty()
                || txtResena.getText().isEmpty() || rutaImagen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor llena todos los campos.");
            return;
        }

        try {
            Serie serie = new Serie(
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

            if (catalogo.agregarSerie(serie)) {
                JOptionPane.showMessageDialog(this, "Serie agregada correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe una serie con ese título.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Año, duración y calificación deben ser numéricos.");
        }
    }
}
