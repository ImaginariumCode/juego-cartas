import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gamesystem.*;

public class Juego extends JFrame {
    private Jugador[] jugadores;
    private JTabbedPane tpJugadores;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    private JButton btnRepartir;
    private JButton btnVerificar;
    
    public Juego() {
        initComponents();
        jugadores = new Jugador[2];
        for(int i = 0; i < jugadores.length; i++)
            jugadores[i] = new Jugador();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Juego de Apuntado");
        
        // Crear componentes
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();
        btnRepartir = new JButton("Repartir");
        btnVerificar = new JButton("Verificar");
        
        // Configurar paneles
        pnlJugador1.setLayout(null);
        pnlJugador2.setLayout(null);
        
        // Configurar colores de fondo
        pnlJugador1.setBackground(new Color(135, 206, 235)); // Azul claro
        pnlJugador2.setBackground(new Color(147, 112, 219)); // Morado
        
        // Agregar pestañas
        tpJugadores.addTab("Pablo", pnlJugador1);
        tpJugadores.addTab("Melani", pnlJugador2);
        
        // Configurar botones
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirActionPerformed(evt);
            }
        });
        
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        
        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRepartir);
        buttonPanel.add(btnVerificar);
        
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(tpJugadores, BorderLayout.CENTER);
        
        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
    
    private void btnRepartirActionPerformed(ActionEvent evt) {
        for(int i = 0; i < jugadores.length; i++)
            jugadores[i].repartir();
        jugadores[0].mostrar(pnlJugador1, false);
        jugadores[1].mostrar(pnlJugador2, false);
    }
    
    private void btnVerificarActionPerformed(ActionEvent evt) {
        int pestaña = tpJugadores.getSelectedIndex();
        JOptionPane.showMessageDialog(this, jugadores[pestaña].obtenerFiguras());
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }
}
