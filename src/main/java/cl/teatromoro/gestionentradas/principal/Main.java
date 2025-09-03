package cl.teatromoro.gestionentradas.principal;

import cl.teatromoro.gestionentradas.clasesbasicas.Entrada;
import cl.teatromoro.gestionentradas.clasesbasicas.Tarifa;
import java.util.Scanner;

/**
 * Clase principal que contiene el método main.
 *
 * Aplicación de abstracción:
 * - Abstracción procedimental: uso de métodos de otras clases (Entrada y Tarifa).
 * - Abstracción de control: manejo de flujos con ciclos y condicionales.
 *
 * Tipos de algoritmos aplicados:
 * - Entrada/Salida: mostrar menús y leer datos del usuario.
 * - Selección: if, if-else, switch y operador ternario.
 * - Repetición: for, while y do-while.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Menú principal usando ciclo iterativo 'for' ---
        // Este for permite mostrar el menú varias veces
        for (int i = 1; i <= 5; i++) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            int opcionMenu = 0;

            // --- Validación de menú ---
            if (sc.hasNextInt()) {
                opcionMenu = sc.nextInt();
            } else {
                System.out.println("❌ Entrada inválida. Ingrese un número.");
                sc.next();
                continue; // vuelve al inicio del for
            }

            // Salir del programa si selecciona 2
            if (opcionMenu == 2) {
                System.out.println("👋 Gracias por usar el sistema. ¡Adiós!");
                break; // rompe el ciclo for
            } else if (opcionMenu != 1) {
                System.out.println("❌ Opción inválida, intente nuevamente.");
                continue;
            }

            // --- Selección del tipo de entrada usando while ---
            int opcionEntrada = 0;
            while (true) { // ciclo repetitivo hasta que el usuario ingrese un valor válido
                System.out.println("\nSeleccione el tipo de entrada:");
                System.out.println("1. VIP");
                System.out.println("2. Platea baja");
                System.out.println("3. Platea alta");
                System.out.println("4. Palcos");

                if (sc.hasNextInt()) { // validación de tipo de dato
                    opcionEntrada = sc.nextInt();
                    if (opcionEntrada >= 1 && opcionEntrada <= 4) break; // valor válido
                    else System.out.println("❌ Número fuera de rango. Intente nuevamente.");
                } else {
                    System.out.println("❌ Entrada inválida. Ingrese un número.");
                    sc.next(); // limpiar entrada inválida
                }
            }

            // --- Conversión de opción a texto usando switch ---
            // Switch = selección múltiple
            String tipoEntrada = switch (opcionEntrada) {
                case 1 -> "vip";
                case 2 -> "platea baja";
                case 3 -> "platea alta";
                case 4 -> "palcos";
                default -> ""; // nunca se alcanza por validación previa
            };

            // --- Selección de zona ---
            String zona = "";
            while (true) { // repetir hasta ingresar A, B o C
                System.out.println("\nPlano del teatro:");
                System.out.println("[A] Zona A (frente al escenario)");
                System.out.println("[B] Zona B (centro)");
                System.out.println("[C] Zona C (laterales)");
                System.out.print("Seleccione la zona (A, B o C): ");
                zona = sc.next().toUpperCase(); // convertir a mayúscula

                // Validación con if
                if (zona.equals("A") || zona.equals("B") || zona.equals("C")) {
                    break; // zona válida
                } else {
                    System.out.println("❌ Zona inválida. Intente nuevamente.");
                }
            }

            // --- Solicitar edad ---
            int edad = 0;
            while (true) {
                System.out.print("Ingrese su edad: ");
                if (sc.hasNextInt()) {
                    edad = sc.nextInt();
                    if (edad > 0 && edad < 120) break; // edad válida
                    else System.out.println("❌ Edad fuera de rango. Intente nuevamente.");
                } else {
                    System.out.println("❌ Entrada inválida. Ingrese un número.");
                    sc.next(); // limpiar
                }
            }

            // --- Selección de tarifa ---
            int opcionTarifa = 0;
            while (true) {
                System.out.println("\nSeleccione la tarifa:");
                System.out.println("1. Estudiante");
                System.out.println("2. Público general");

                if (sc.hasNextInt()) {
                    opcionTarifa = sc.nextInt();
                    if (opcionTarifa == 1 || opcionTarifa == 2) break;
                    else System.out.println("❌ Número fuera de rango. Intente nuevamente.");
                } else {
                    System.out.println("❌ Entrada inválida. Ingrese un número.");
                    sc.next();
                }
            }

            // Operador ternario para determinar tipo de tarifa
            String tipoTarifa = (opcionTarifa == 1) ? "estudiante" : "general";

            // --- Creación de objetos ---
            // Abstracción de datos: encapsula tipo de entrada, tarifa y precio
            Tarifa tarifa = new Tarifa(tipoTarifa, edad);
            Entrada entrada = new Entrada(tipoEntrada, tarifa);

            // --- Cálculo del precio final con descuento ---
            // Usamos ciclo do-while para asegurar que el cálculo se realiza al menos una vez
            int precioFinal = 0;
            int contador = 0;
            do {
                precioFinal = tarifa.obtenerPrecio(tipoEntrada); // aplica descuento según edad o estudiante
                contador++;
            } while (contador < 1); // solo una iteración, pero demostrando do-while

            // --- Salida: resumen de la compra ---
            System.out.println("\n✅ Entrada generada con éxito:");
            System.out.println("Tipo de entrada: " + entrada.getTipoEntrada());
            System.out.println("Zona seleccionada: " + zona);
            System.out.println("Tarifa: " + tarifa.getTipoTarifa());
            System.out.println("Edad del comprador: " + edad);
            System.out.println("Precio final a pagar: $" + precioFinal);
            System.out.println("Gracias por su compra, disfrute la función.");
        }

        sc.close(); // cerrar recurso Scanner
    }
}
