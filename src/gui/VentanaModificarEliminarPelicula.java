/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import datos.CatalogoPeliculas;
import datos.Pelicula;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class VentanaModificarEliminarPelicula extends JFrame {

    private CatalogoPeliculas catalogo;
    private JComboBox<String> comboPeliculas;
    private JTextField txtTitulo, txtAnio, txtDuracion, txtClasificacion, txtIdioma, txtActores, txtGenero, txtCalificacion, txtImagen;
    private JTextArea txtResena;
    private Stack<Pelicula> historialCambios = new Stack<>();

    public VentanaModificarEliminarPelicula() {
        setTitle("Modificar / Eliminar Película");
        setSize(550, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        catalogo = new CatalogoPeliculas();

        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        comboPeliculas = new JComboBox<>();
        for (Pelicula p : catalogo.getPeliculas()) {
            comboPeliculas.addItem(p.getTitulo());
        }
        comboPeliculas.addActionListener(e -> cargarDatosPelicula());

        JLabel lblSelecciona = crearEtiqueta("Selecciona Película:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblSelecciona, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(comboPeliculas, gbc);

        int fila = 1;
        txtTitulo = new JTextField();
        panel.add(crearEtiqueta("Título:"), crearGbc(0, fila));
        panel.add(txtTitulo, crearGbc(1, fila++));

        txtImagen = new JTextField();
        panel.add(crearEtiqueta("Imagen (ruta):"), crearGbc(0, fila));
        panel.add(txtImagen, crearGbc(1, fila++));

        txtAnio = new JTextField();
        panel.add(crearEtiqueta("Año:"), crearGbc(0, fila));
        panel.add(txtAnio, crearGbc(1, fila++));

        txtDuracion = new JTextField();
        panel.add(crearEtiqueta("Duración (min):"), crearGbc(0, fila));
        panel.add(txtDuracion, crearGbc(1, fila++));

        txtClasificacion = new JTextField();
        panel.add(crearEtiqueta("Clasificación:"), crearGbc(0, fila));
        panel.add(txtClasificacion, crearGbc(1, fila++));

        txtIdioma = new JTextField();
        panel.add(crearEtiqueta("Idioma:"), crearGbc(0, fila));
        panel.add(txtIdioma, crearGbc(1, fila++));

        txtActores = new JTextField();
        panel.add(crearEtiqueta("Actores:"), crearGbc(0, fila));
        panel.add(txtActores, crearGbc(1, fila++));

        txtResena = new JTextArea(4, 20);
        txtResena.setLineWrap(true);
        txtResena.setWrapStyleWord(true);
        txtResena.setFont(new Font("Arial", Font.PLAIN, 14));
        txtResena.setBackground(new Color(60, 60, 60));
        txtResena.setForeground(Color.WHITE);
        JScrollPane scrollResena = new JScrollPane(txtResena);
        scrollResena.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(crearEtiqueta("Reseña:"), crearGbc(0, fila));
        panel.add(scrollResena, crearGbc(1, fila++));

        txtGenero = new JTextField();
        panel.add(crearEtiqueta("Género:"), crearGbc(0, fila));
        panel.add(txtGenero, crearGbc(1, fila++));

        txtCalificacion = new JTextField();
        panel.add(crearEtiqueta("Calificación (0-5):"), crearGbc(0, fila));
        panel.add(txtCalificacion, crearGbc(1, fila++));

        JButton btnModificar = new JButton("Modificar");
        estilizarBotonNetflix(btnModificar);
        btnModificar.addActionListener(e -> modificarPelicula());

        JButton btnEliminar = new JButton("Eliminar");
        estilizarBotonNetflix(btnEliminar);
        btnEliminar.addActionListener(e -> eliminarPelicula());

        JButton btnDeshacer = new JButton("Deshacer");
        estilizarBotonNetflix(btnDeshacer);
        btnDeshacer.addActionListener(e -> deshacerCambio());

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(40, 40, 40));
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnDeshacer);

        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        add(panel);
        cargarDatosPelicula();
        setVisible(true);
    }

    private void cargarDatosPelicula() {
        String tituloSeleccionado = (String) comboPeliculas.getSelectedItem();
        if (tituloSeleccionado == null) return;

        for (Pelicula p : catalogo.getPeliculas()) {
            if (p.getTitulo().equalsIgnoreCase(tituloSeleccionado)) {
                txtTitulo.setText(p.getTitulo());
                txtImagen.setText(p.getRutaImagen());
                txtAnio.setText(String.valueOf(p.getAnio()));
                txtDuracion.setText(String.valueOf(p.getDuracion()));
                txtClasificacion.setText(p.getClasificacion());
                txtIdioma.setText(p.getIdioma());
                txtActores.setText(p.getActores());
                txtResena.setText(p.getResena());
                txtGenero.setText(p.getGenero());
                txtCalificacion.setText(String.valueOf(p.getCalificacion()));
                break;
            }
        }
    }

    private void modificarPelicula() {
        String originalTitulo = (String) comboPeliculas.getSelectedItem();
        if (originalTitulo == null) return;

        for (Pelicula p : catalogo.getPeliculas()) {
            if (p.getTitulo().equalsIgnoreCase(originalTitulo)) {
                try {
                    if (txtTitulo.getText().isEmpty() || txtAnio.getText().isEmpty() || txtDuracion.getText().isEmpty()
                        || txtClasificacion.getText().isEmpty() || txtIdioma.getText().isEmpty() || txtActores.getText().isEmpty()
                        || txtCalificacion.getText().isEmpty() || txtGenero.getText().isEmpty() || txtResena.getText().isEmpty()
                        || txtImagen.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Por favor llena todos los campos.");
                        return;
                    }

                    Pelicula copiaAnterior = new Pelicula(p.getTitulo(), p.getRutaImagen(), p.getAnio(), p.getDuracion(),
                            p.getClasificacion(), p.getIdioma(), p.getActores(), p.getResena(), p.getGenero(), p.getCalificacion());
                    historialCambios.push(copiaAnterior);

                    p.setTitulo(txtTitulo.getText());
                    p.setRutaImagen(txtImagen.getText());
                    p.setAnio(Integer.parseInt(txtAnio.getText()));
                    p.setDuracion(Integer.parseInt(txtDuracion.getText()));
                    p.setClasificacion(txtClasificacion.getText());
                    p.setIdioma(txtIdioma.getText());
                    p.setActores(txtActores.getText());
                    p.setResena(txtResena.getText());
                    p.setGenero(txtGenero.getText());
                    p.setCalificacion(Double.parseDouble(txtCalificacion.getText()));

                    catalogo.eliminarPelicula(originalTitulo);
                    catalogo.agregarPelicula(p);

                    JOptionPane.showMessageDialog(this, "Película modificada correctamente.");
                    dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Año, duración y calificación deben ser números válidos.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al modificar la película.");
                }
                break;
            }
        }
    }

    private void eliminarPelicula() {
        String tituloSeleccionado = (String) comboPeliculas.getSelectedItem();
        if (tituloSeleccionado == null) return;

        for (Pelicula p : catalogo.getPeliculas()) {
            if (p.getTitulo().equalsIgnoreCase(tituloSeleccionado)) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar la película?");
                if (confirm == JOptionPane.YES_OPTION) {
                    historialCambios.push(new Pelicula(p.getTitulo(), p.getRutaImagen(), p.getAnio(), p.getDuracion(),
                            p.getClasificacion(), p.getIdioma(), p.getActores(), p.getResena(), p.getGenero(), p.getCalificacion()));

                    boolean eliminado = catalogo.eliminarPelicula(tituloSeleccionado);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(this, "Película eliminada correctamente.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo eliminar la película.");
                    }
                }
                break;
            }
        }
    }

    private void deshacerCambio() {
        if (historialCambios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cambios que deshacer.");
            return;
        }

        Pelicula ultima = historialCambios.pop();
        catalogo.agregarPelicula(ultima);
        JOptionPane.showMessageDialog(this, "Última acción deshecha. Película restaurada: " + ultima.getTitulo());
        dispose();
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 16));
        return etiqueta;
    }

    private GridBagConstraints crearGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        return gbc;
    }

    private void estilizarBotonNetflix(JButton boton) {
        boton.setBackground(new Color(229, 9, 20));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}

