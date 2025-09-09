package cl.teatromoro.gestionentradas.clasesbasicas;

/**
 * Clase que representa una Entrada de Teatro.
 *
 * Variables estáticas:
 * - contadorEntradas: genera IDs únicos para cada entrada.
 *
 * Variables de instancia:
 * - numeroEntrada: identificador único.
 * - tipoEntrada: tipo seleccionado por el usuario (VIP, Platea, etc.).
 * - zona: zona A, B o C del teatro.
 * - tarifa: objeto Tarifa asociado.
 * - precio: precio calculado con descuentos.
 */
public class Entrada {
    private static int contadorEntradas = 1; // variable estática para IDs

    private int numeroEntrada;
    private String tipoEntrada;
    private String zona;
    private Tarifa tarifa;
    private int precio;

    // Constructor
    public Entrada(String tipoEntrada, Tarifa tarifa, String zona) {
        this.numeroEntrada = contadorEntradas++;
        this.tipoEntrada = tipoEntrada;
        this.tarifa = tarifa;
        this.zona = zona;
        this.precio = tarifa.obtenerPrecio(tipoEntrada);
    }

    // Getters
    public int getNumeroEntrada() { return numeroEntrada; }
    public String getTipoEntrada() { return tipoEntrada; }
    public String getZona() { return zona; }
    public Tarifa getTarifa() { return tarifa; }
    public int getPrecio() { return precio; }
}
