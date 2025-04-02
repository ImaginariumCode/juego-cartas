import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mazo {
    private List<Carta> cartas = new ArrayList<>();
    private Random random = new Random();

    public Mazo() {
        String[] nombres = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        Pinta[] pintas = Pinta.values();

        for (Pinta pinta : pintas) {
            for (int i = 0; i < nombres.length; i++) {
                cartas.add(new Carta(nombres[i], pinta, (i >= 9) ? 10 : i + 2));
            }
        }
    }

    public Carta robarCarta() {
        return cartas.get(random.nextInt(cartas.size())); // Mazo infinito
    }
}
