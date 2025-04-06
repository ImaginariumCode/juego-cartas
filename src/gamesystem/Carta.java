package gamesystem;

import java.util.*;
import javax.swing.*;

public class Carta {
    private int indice;
    
    public Carta(Random r) {
        indice = r.nextInt(52) + 1;
    }
    
    public PintaCarta obtenerPinta() {
        if(indice <= 13)
            return PintaCarta.TREBOL;
        else if (indice <= 26)
            return PintaCarta.PICA;
        else if (indice <= 39)
            return PintaCarta.CORAZON;
        else
            return PintaCarta.DIAMANTE;
    }
    
    public NombreCarta obtenerNombre() {
        int numero = indice % 13;
        if (numero == 0) {
            numero = 13;
        }
        return NombreCarta.values()[numero];
    }
    
    public int obtenerValor() {
        NombreCarta nombre = obtenerNombre();
        if (nombre == NombreCarta.ACE || nombre == NombreCarta.JACK || 
            nombre == NombreCarta.QUEEN || nombre == NombreCarta.KING)
            return 10;
        return nombre.ordinal();
    }
    
    public void mostrarCarta(int x, int y, JPanel pnl, boolean tapada) {
        String nombreImagen;
        if(tapada)
            nombreImagen = "/imagenes/CARTA1.JPG";
        else
            nombreImagen = "/imagenes/CARTA" + String.valueOf(indice) + ".JPG";
            
        ImageIcon imagen = new ImageIcon(getClass().getResource(nombreImagen));
        JLabel lblCarta = new JLabel(imagen);
        lblCarta.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());
        pnl.add(lblCarta);
    }
    
    public int getIndice() {
        return indice;
    }
} 