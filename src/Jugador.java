package juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Jugador {
    private List<Carta> cartas;
    private Random r;
    private String nombre;

    public Jugador(String nombre) {
        this.cartas = new ArrayList<>();
        this.r = new Random();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método para obtener grupos en escalera de la misma pinta
    public List<List<Carta>> obtenerEscaleras() {
        List<List<Carta>> escaleras = new ArrayList<>();

        // Agrupar cartas por pinta
        for (Carta carta : cartas) {
            List<Carta> escalera = new ArrayList<>();
            escalera.add(carta);

            for (Carta otraCarta : cartas) {
                if (!escalera.contains(otraCarta) &&
                    otraCarta.getPinta().equals(carta.getPinta()) &&
                    otraCarta.getValor() == carta.getValor() + 1) {
                    escalera.add(otraCarta);
                }
            }

            if (escalera.size() >= 3) escaleras.add(escalera);
        }

        return escaleras;
    }

    // Método para calcular el puntaje del jugador
    public int calcularPuntaje() {
        List<List<Carta>> escaleras = obtenerEscaleras();
        int puntaje = 0;

        for (Carta carta : cartas) {
            boolean enEscalera = false;
            for (List<Carta> escalera : escaleras) {
                if (escalera.contains(carta)) {
                    enEscalera = true;
                    break;
                }
            }
            if (!enEscalera) {
                puntaje += switch (carta.getNombre()) {
                    case "Ace", "Jack", "Queen", "King" -> 10;
                    default -> carta.getValor();
                };
            }
        }

        return puntaje;
    }

    // Método para repartir cartas al jugador desde un mazo
    public void repartir(Mazo mazo) {
        cartas.clear();
        for (int i = 0; i < 5; i++) {
            cartas.add(mazo.robarCarta());
        }
    }

    // Método para mostrar las cartas en la interfaz gráfica
    public void mostrar(JPanel panel) {
        panel.removeAll();
        for (Carta carta : cartas) {
            JLabel label = new JLabel(carta.toString());
            panel.add(label);
        }
        panel.revalidate();
        panel.repaint();
    }

    // Método para verificar si el jugador ha bajado todas sus cartas
    public boolean haBajadoTodasLasCartas() {
        return cartas.isEmpty();
    }

    // Método para bajar todas las cartas
    public void bajarCartas() {
        cartas.clear();
    }

    // Método para obtener los grupos de cartas
    public String getGrupos() {
        if (cartas.isEmpty()) return "No hay cartas en la mano.";

        StringBuilder gruposEncontrados = new StringBuilder("Grupos encontrados:\n");

        for (int i = 0; i < cartas.size(); i++) {
            for (int j = i + 1; j < cartas.size(); j++) {
                if (cartas.get(i).getValor() == cartas.get(j).getValor()) {
                    gruposEncontrados.append(cartas.get(i).toString()).append(" y ").append(cartas.get(j).toString()).append("\n");
                }
            }
        }

        return gruposEncontrados.toString().isEmpty() ? "No se encontraron grupos." : gruposEncontrados.toString();
    }
}
