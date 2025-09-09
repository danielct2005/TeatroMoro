package cl.teatromoro.gestionentradas.clasesbasicas;

/**
 * Clase que representa una Tarifa (Estudiante, Público General o Tercera Edad).
 *
 * Variables de instancia:
 * - tipoTarifa: "estudiante" o "general".
 * - edad: edad del comprador.
 *
 * Métodos:
 * - obtenerPrecio: calcula el precio base según el tipo de entrada,
 *   aplicando descuentos si corresponde.
 */
public class Tarifa {

    private String tipoTarifa; // "estudiante" o "general"
    private int edad;          // edad del comprador

    // Constructor
    public Tarifa(String tipoTarifa, int edad) {
        this.tipoTarifa = tipoTarifa;
        this.edad = edad;
    }

    // Getters
    public String getTipoTarifa() {
        return tipoTarifa;
    }

    public int getEdad() {
        return edad;
    }

    /**
     * Calcula el precio final según tipo de entrada y descuentos.
     * @param tipoEntrada tipo de entrada (VIP, platea, etc.)
     * @return precio final con descuentos aplicados
     */
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
