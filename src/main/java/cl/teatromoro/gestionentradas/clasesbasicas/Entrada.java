package cl.teatromoro.gestionentradas.clasesbasicas;

/**
 * Clase que representa una Entrada de Teatro.
 *
 * Abstracción aplicada:
 * - Encapsulación: atributos privados tipoEntrada, tarifa y precio.
 * - Asociación: referencia a la clase Tarifa.
 * - Abstracción de datos: encapsula toda la información de la entrada.
 */
public class Entrada {

    private String tipoEntrada;
    private Tarifa tarifa;   // Asociación con Tarifa
    private int precio;

    // Constructor: inicializa atributos (algoritmo de secuencia)
    public Entrada(String tipoEntrada, Tarifa tarifa) {
        this.tipoEntrada = tipoEntrada;
        this.tarifa = tarifa;
        this.precio = tarifa.obtenerPrecio(tipoEntrada); // delegación
    }

    // Getters (encapsulación)
    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public int getPrecio() {
        return precio;
    }
}
