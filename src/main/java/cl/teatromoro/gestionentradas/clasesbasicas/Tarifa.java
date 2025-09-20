package cl.teatromoro.gestionentradas.clasesbasicas;

/**
 * Representa una tarifa de entrada al teatro.
 * Ejemplos: General, VIP, Palco.
 */
public class Tarifa {
    private String tipo;
    private int precio;

    public Tarifa(String tipo, int precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public String mostrarTarifa() {
        return tipo + " - $" + precio;
    }

    @Override
    public String toString() {
        return "Tarifa: " + tipo + " ($" + precio + ")";
    }
}
