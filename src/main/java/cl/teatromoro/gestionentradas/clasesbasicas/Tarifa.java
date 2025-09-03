package cl.teatromoro.gestionentradas.clasesbasicas;

/**
 * Clase que representa una Tarifa (Estudiante, Público General o Tercera Edad).
 *
 * Abstracción aplicada:
 * - Abstracción de datos: objeto Tarifa encapsula tipo y edad.
 * - Encapsulación: atributos privados con getter.
 * - Algoritmo de selección múltiple: switch para calcular precio.
 */
public class Tarifa {

    private String tipoTarifa; // "estudiante" o "general"
    private int edad;          // edad del comprador

    // Constructor
    public Tarifa(String tipoTarifa, int edad) {
        this.tipoTarifa = tipoTarifa;
        this.edad = edad;
    }

    // Getter
    public String getTipoTarifa() {
        return tipoTarifa;
    }

    // --- Método que calcula precio con descuentos ---
    public int obtenerPrecio(String tipoEntrada) {
        int precioBase = switch (tipoEntrada.toLowerCase()) {
            case "vip" -> 35000;
            case "platea baja" -> 25000;
            case "platea alta" -> 15000;
            case "palcos" -> 11000;
            default -> 0;
        };

        int descuento = 0;

        // IF-ELSE: decide qué descuento aplicar
        if (tipoTarifa.equalsIgnoreCase("estudiante")) {
            descuento = (int)(precioBase * 0.10); // 10% descuento
        } else if (edad >= 65) {
            descuento = (int)(precioBase * 0.15); // 15% tercera edad
        }

        return precioBase - descuento;
    }
}
