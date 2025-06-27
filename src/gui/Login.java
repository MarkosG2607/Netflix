/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public Login() {
        setTitle("Sexflix - Login");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(Color.BLACK);
        panelFondo.setLayout(new BorderLayout());
        panelFondo.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        
        JLabel logoSexflix = new JLabel();
        ImageIcon iconoLogo = cargarIcono("/imagenes/sexflix_logo.png", 200, 60);
        if (iconoLogo != null) {
            logoSexflix.setIcon(iconoLogo);
        } else {
            logoSexflix.setText("SEXFLIX");
            logoSexflix.setForeground(Color.RED);
            logoSexflix.setFont(new Font("Arial", Font.BOLD, 28));
        }
        logoSexflix.setHorizontalAlignment(SwingConstants.CENTER);
        panelFondo.add(logoSexflix, BorderLayout.NORTH);

        
        JPanel panelCentro = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCentro.setBackground(Color.BLACK);

        JLabel labelTipo = new JLabel("Selecciona tipo de usuario:", SwingConstants.CENTER);
        labelTipo.setForeground(Color.RED);
        labelTipo.setFont(new Font("Arial", Font.BOLD, 20));
        panelCentro.add(labelTipo);
        panelCentro.add(new JLabel()); 

       
        JButton btnAdmin = crearBotonNetflix("Administrador", "/imagenes/admin_icon.png");
        btnAdmin.addActionListener(e -> {
            
            JPanel panelPass = new JPanel();
            panelPass.setBackground(Color.BLACK);
            panelPass.setLayout(new BorderLayout(5, 5));

            JLabel labelPass = new JLabel("Password:");
            labelPass.setForeground(new Color(229, 9, 20)); 
            labelPass.setFont(new Font("Arial", Font.BOLD, 16));

            JPasswordField pf = new JPasswordField(15);
            pf.setBackground(new Color(40, 40, 40));  
            pf.setForeground(Color.WHITE);            
            pf.setCaretColor(Color.WHITE);
            pf.setFont(new Font("Arial", Font.PLAIN, 16));
            pf.setBorder(BorderFactory.createLineBorder(new Color(229, 9, 20), 2)); 

            panelPass.add(labelPass, BorderLayout.NORTH);
            panelPass.add(pf, BorderLayout.CENTER);

            int okCxl = JOptionPane.showConfirmDialog(
                    this, panelPass, "Ingresa contraseña de administrador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (okCxl == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("yisus123")) {  
                    new InterfazSexflix(true).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        JButton btnUsuario = crearBotonNetflix("Usuario", "/imagenes/user_icon.png");
        btnUsuario.addActionListener(e -> {
            new InterfazSexflix(false).setVisible(true);
            dispose();
        });

        panelCentro.add(btnAdmin);
        panelCentro.add(btnUsuario);

        panelFondo.add(panelCentro, BorderLayout.CENTER);

        add(panelFondo, BorderLayout.CENTER);
    }

    private JButton crearBotonNetflix(String texto, String rutaIcono) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(229, 9, 20)); // rojo Netflix
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        ImageIcon icono = cargarIcono(rutaIcono, 25, 25);
        if (icono != null) {
            boton.setIcon(icono);
            boton.setHorizontalTextPosition(SwingConstants.RIGHT);
            boton.setIconTextGap(10);
        }

        return boton;
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        java.net.URL imgURL = getClass().getResource(ruta);
        if (imgURL != null) {
            ImageIcon icono = new ImageIcon(imgURL);
            Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenEscalada);
        } else {
            System.err.println("No se encontró el recurso: " + ruta);
            return null;
        }
    }
}


