package gamesystem;

import java.util.*;
import javax.swing.*;

public class Jugador {
    private Random r;
    private Carta[] cartas;
    private Figura[] figuras;
    private NombreCarta[] cartasFigura;
    
    public Jugador() {
        r = new Random();
    }
    
    public void repartir() {
        cartas = new Carta[10];
        for(int i = 0; i < 10; i++)
            cartas[i] = new Carta(r);
    }
    
    public void mostrar(JPanel pnl, boolean tapada) {
        pnl.removeAll();
        for(int i = 0; i < 10; i++)
            cartas[i].mostrarCarta(5 + i * 40, 5, pnl, tapada);
        pnl.repaint();
    }
    
    public String obtenerFiguras() {
        String mensaje = "";
        
        // Obtener figuras por nombre
        int[] contadores = new int[13];
        for(int i = 0; i < 10; i++)
            contadores[cartas[i].obtenerNombre().ordinal() - 1]++;
            
        boolean hayFiguras = false;
        for(int i = 0; i < 13; i++) {
            if(contadores[i] >= 2) {
                hayFiguras = true;
                String tipoFigura;
                if(contadores[i] == 2) {
                    tipoFigura = "PAR";
                } else if(contadores[i] == 3) {
                    tipoFigura = "TERNA";
                } else if(contadores[i] == 4) {
                    tipoFigura = "CUARTA";
                } else {
                    tipoFigura = "FIGURA DE " + contadores[i];
                }
                
                mensaje = mensaje + tipoFigura + " de " + NombreCarta.values()[i + 1] + "\n";
            }
        }
        
        // Obtener escaleras por pinta
        Map<PintaCarta, List<Carta>> cartasPorPinta = new HashMap<>();
        for(PintaCarta pinta : PintaCarta.values()) {
            if(pinta != PintaCarta.NINGUNO)
                cartasPorPinta.put(pinta, new ArrayList<>());
        }
        
        for(Carta carta : cartas) {
            cartasPorPinta.get(carta.obtenerPinta()).add(carta);
        }
        
        // Lista para almacenar las cartas usadas en escaleras
        List<Carta> cartasEnEscalera = new ArrayList<>();
        
        for(Map.Entry<PintaCarta, List<Carta>> entry : cartasPorPinta.entrySet()) {
            List<Carta> cartasPinta = entry.getValue();
            if(cartasPinta.size() >= 2) {
                // Ordenar las cartas por nombre (valor)
                Collections.sort(cartasPinta, (c1, c2) -> c1.obtenerNombre().ordinal() - c2.obtenerNombre().ordinal());
                
                // Eliminar duplicados (mismo valor)
                List<Carta> cartasSinDuplicados = new ArrayList<>();
                for(int i = 0; i < cartasPinta.size(); i++) {
                    if(i == 0 || cartasPinta.get(i).obtenerNombre() != cartasPinta.get(i-1).obtenerNombre()) {
                        cartasSinDuplicados.add(cartasPinta.get(i));
                    }
                }
                
                // Buscar escaleras
                int longitudEscalera = 1;
                int maxLongitud = 1;
                List<Carta> escaleraActual = new ArrayList<>();
                escaleraActual.add(cartasSinDuplicados.get(0));
                
                for(int i = 1; i < cartasSinDuplicados.size(); i++) {
                    if(cartasSinDuplicados.get(i).obtenerNombre().ordinal() == 
                        cartasSinDuplicados.get(i-1).obtenerNombre().ordinal() + 1) {
                        // Es una carta consecutiva
                        longitudEscalera++;
                        escaleraActual.add(cartasSinDuplicados.get(i));
                        maxLongitud = Math.max(maxLongitud, longitudEscalera);
                    } else {   
                        if(longitudEscalera >= 2) {
                            // Guardar las cartas de la escalera encontrada
                            cartasEnEscalera.addAll(escaleraActual);
                        }
                        longitudEscalera = 1;
                        escaleraActual = new ArrayList<>();
                        escaleraActual.add(cartasSinDuplicados.get(i));
                    }
                }
                
                // Verificar la última escalera
                if(longitudEscalera >= 2) {
                    cartasEnEscalera.addAll(escaleraActual);
                }
                
                if(maxLongitud >= 2) {
                    hayFiguras = true;
                    mensaje = mensaje + "Escalera de " + maxLongitud + 
                            " cartas de " + entry.getKey() + "\n";
                }
            }
        }
        
        // Calcular puntaje
        int puntaje = 0;
        boolean[] cartasUsadas = new boolean[10];
        
        for(int i = 0; i < 10; i++) {
            if(contadores[cartas[i].obtenerNombre().ordinal() - 1] >= 2)
                cartasUsadas[i] = true;
        }
        
        // Marcar cartas usadas en escaleras
        for(Carta carta : cartasEnEscalera) {
            for(int i = 0; i < 10; i++) {
                if(cartas[i] == carta) {
                    cartasUsadas[i] = true;
                    break;
                }
            }
        }
        
        // Sumar puntaje de cartas no usadas
        for(int i = 0; i < 10; i++) {
            if(!cartasUsadas[i])
                puntaje += cartas[i].obtenerValor();
        }
        
        if(!hayFiguras)
            mensaje = mensaje + "No hay figuras\n";
            
        mensaje = mensaje + "Puntaje: " + puntaje;
        
        return mensaje;
    }
} 