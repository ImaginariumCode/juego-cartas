public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Mazo mazo;
    private int turnoActual; // 0: Jugador 1, 1: Jugador 2

    public Juego() {
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
        mazo = new Mazo();
        turnoActual = 0; // Empieza Jugador 1
    }

    public void jugarTurno() {
        Jugador jugadorActual = (turnoActual == 0) ? jugador1 : jugador2;

        System.out.println("\nTurno de " + jugadorActual.getNombre());
        jugadorActual.robarCarta(mazo);
        jugadorActual.mostrarCartas();
        System.out.println("Puntaje actual: " + jugadorActual.calcularPuntaje());

        // Simulación de bajada de cartas (puedes mejorar la lógica)
        if (Math.random() < 0.2) { // Simula que el jugador decide bajar sus cartas
            jugadorActual.bajarCartas();
            System.out.println(jugadorActual.getNombre() + " ha bajado todas sus cartas!");
        }

        // Comprobar si el juego terminó
        if (jugador1.haBajadoTodasLasCartas() && jugador2.haBajadoTodasLasCartas()) {
            determinarGanador();
        } else if (jugador1.haBajadoTodasLasCartas() || jugador2.haBajadoTodasLasCartas()) {
            System.out.println("¡" + jugadorActual.getNombre() + " gana el juego!");
            System.exit(0);
        }

        // Cambiar turno al siguiente jugador
        turnoActual = (turnoActual + 1) % 2;
    }

    private void determinarGanador() {
        int puntajeJ1 = jugador1.calcularPuntaje();
        int puntajeJ2 = jugador2.calcularPuntaje();

        System.out.println("\nAmbos jugadores han bajado sus cartas.");
        System.out.println("Puntaje Jugador 1: " + puntajeJ1);
        System.out.println("Puntaje Jugador 2: " + puntajeJ2);

        if (puntajeJ1 == puntajeJ2) {
            System.out.println("¡Es un empate!");
        } else {
            System.out.println("¡Gana " + (puntajeJ1 < puntajeJ2 ? "Jugador 1" : "Jugador 2") + "!");
        }

        System.exit(0);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();

        while (true) {
            juego.jugarTurno();
        }
    }
}
