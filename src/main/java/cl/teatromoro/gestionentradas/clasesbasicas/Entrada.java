package cl.teatromoro.gestionentradas.clasesbasicas;

/**
 * Clase que representa una entrada comprada.
 * Contiene número de asiento, precio y estado.
 */
public class Entrada {
    private int numero;
    private int precio;
    private String estado;

    public Entrada(int numero, int precio, String estado) {
        this.numero = numero;
        this.precio = precio;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public int getPrecio() {
        return precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Imprime la boleta de esta entrada
     */
    public void imprimirBoleta() {
        System.out.println("Asiento: " + numero + " | Estado: " + estado + " | Precio: $" + precio);
    }
}
