public class Carta {
    private String nombre;
    private Pinta pinta;
    private int valor;

    public Carta(String nombre, Pinta pinta, int valor) {
        this.nombre = nombre;
        this.pinta = pinta;
        this.valor = valor;
    }

    public String getNombre() { return nombre; }
    public Pinta getPinta() { return pinta; }
    public int getValor() { return valor; }

    @Override
    public String toString() {
        return nombre + " de " + pinta;
    }
}
