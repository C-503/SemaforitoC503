package com.c503.semaforo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Semaforo extends JFrame{

    private JPanel panelSemaforo;
    private JPanel panelCarro;
    private JLabel labelCarro;
    private JButton btnIniciar;
    
    private Color colorRojo = Color.RED;
    private Color colorAmarillo = Color.YELLOW;
    private Color colorVerde = Color.GREEN;

    private Timer timer;
    private String estadoActual = "ROJO";
    
    public Semaforo() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("C-503");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {
        panelSemaforo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarSemaforo(g);
            }
        };

        panelSemaforo.setPreferredSize(new Dimension(100, 300));
        add(panelSemaforo, BorderLayout.CENTER);

        panelCarro = new JPanel();
        panelCarro.setPreferredSize(new Dimension(400, 50));
        labelCarro = new JLabel("🚗 🚜 🛥 🏍 🏎 🚂 🚅");
        labelCarro.setFont(new Font("Serif", Font.PLAIN, 30));
        panelCarro.add(labelCarro);
        add(panelCarro, BorderLayout.SOUTH);

        btnIniciar = new JButton("Start Semaforo");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSimulacion();
            }
        });
        add(btnIniciar, BorderLayout.NORTH);
    }

    private void dibujarSemaforo(Graphics g) {
    // Convertir Graphics a Graphics2D
    Graphics2D g2 = (Graphics2D) g;

    // Establecer un borde más grueso
    g2.setStroke(new BasicStroke(5)); // Borde de 5 píxeles de grosor

    // Dibujar el rectángulo del semáforo (relleno)
    g2.setColor(Color.decode("#808080"));
    g2.fillRect(50, 50, 100, 250);

    // Dibujar el borde del rectángulo
    g2.setColor(Color.BLACK);
    g2.drawRect(50, 50, 100, 250);

    // Dibujar el foco rojo
    g2.setColor(estadoActual.equals("ROJO") ? colorRojo : Color.DARK_GRAY);
    g2.fillOval(75, 75, 50, 50);
    g2.setColor(Color.BLACK); // Color del borde
    g2.drawOval(75, 75, 50, 50); // Dibuja el borde grueso

    // Dibujar el foco amarillo
    g2.setColor(estadoActual.equals("AMARILLO") ? colorAmarillo : Color.DARK_GRAY);
    g2.fillOval(75, 150, 50, 50);
    g2.setColor(Color.BLACK); // Color del borde
    g2.drawOval(75, 150, 50, 50); // Dibuja el borde grueso

    // Dibujar el foco verde
    g2.setColor(estadoActual.equals("VERDE") ? colorVerde : Color.DARK_GRAY);
    g2.fillOval(75, 225, 50, 50);
    g2.setColor(Color.BLACK); // Color del borde
    g2.drawOval(75, 225, 50, 50); // Dibuja el borde grueso
}


    private void iniciarSimulacion() {
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarEstado();
                moverCarro();
                panelSemaforo.repaint();
            }
        });
        timer.start();
    }

    private void cambiarEstado() {
        switch (estadoActual) {
            case "ROJO":
                estadoActual = "VERDE";
                break;
            case "VERDE":
                estadoActual = "AMARILLO";
                break;
            case "AMARILLO":
                estadoActual = "ROJO";
                break;
        }
    }

    private void moverCarro() {
        if (estadoActual.equals("VERDE")) {
            labelCarro.setText("⬅ 🚗 🚜 🛥 🏍 🏎 🚂 🚅");
        } else {
            labelCarro.setText("🚗 🚜 🛥 🏍 🏎 🚂 🚅");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Semaforo().setVisible(true);
            }
        });
    }
}
