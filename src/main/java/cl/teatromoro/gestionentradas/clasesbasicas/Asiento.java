package cl.teatromoro.gestionentradas.clasesbasicas;

/**
 * Representa un asiento dentro del teatro.
 * Puede estar en estado: libre, reservado o vendido.
 */
public class Asiento {
    private int numero;
    private String estado; // libre, reservado, vendido

    public Asiento(int numero) {
        this.numero = numero;
        this.estado = "libre";
    }

    public int getNumero() {
        return numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Asiento " + numero + " - Estado: " + estado;
    }
}
