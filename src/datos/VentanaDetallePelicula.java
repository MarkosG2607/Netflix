/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author lenovo
 */
import datos.Pelicula;

import javax.swing.*;
import java.awt.*;

public class VentanaDetallePelicula extends JFrame {

    public VentanaDetallePelicula(Pelicula pelicula) {
        setTitle(pelicula.getTitulo());
        setSize(900, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        // Panel superior con imagen de portada
        JLabel lblPortada = new JLabel();
        ImageIcon iconPortada = new ImageIcon(pelicula.getRutaImagen());
        Image imgPortada = iconPortada.getImage().getScaledInstance(300, 450, Image.SCALE_SMOOTH);
        lblPortada.setIcon(new ImageIcon(imgPortada));
        lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
        lblPortada.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Simulación de video con imagen de referencia (puedes usar una imagen .png de un reproductor)
        JLabel lblSimuladorVideo = new JLabel();
        ImageIcon iconSimulador = new ImageIcon("simulador_video.png"); // Imagen que simula video
        Image imgSimulador = iconSimulador.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        lblSimuladorVideo.setIcon(new ImageIcon(imgSimulador));
        lblSimuladorVideo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSimuladorVideo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Panel contenedor
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.BLACK);
        panelCentral.add(lblPortada);
        panelCentral.add(lblSimuladorVideo);

        add(panelCentral, BorderLayout.CENTER);

        setVisible(true);
    }
}
