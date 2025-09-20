package cl.teatromoro.gestionentradas.principal;

import cl.teatromoro.gestionentradas.clasesbasicas.Asiento;
import cl.teatromoro.gestionentradas.clasesbasicas.Entrada;
import cl.teatromoro.gestionentradas.clasesbasicas.Tarifa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para la gestión de reservas y compras de entradas
 * en el Teatro Moro.
 *
 * Menú disponible:
 * 1. Reservar asiento
 * 2. Modificar reserva (cambio de asiento)
 * 3. Comprar entradas reservadas
 * 4. Imprimir boleta
 * 5. Salir
 */
public class Main {
    private static final String NOMBRE_TEATRO = "Teatro Moro";
    private static final int CAPACIDAD = 10; // total de asientos
    private static Asiento[] asientos = new Asiento[CAPACIDAD];
    private static int vendidos = 0;
    private static int reservados = 0;

    // Tarifas disponibles
    private static Tarifa tarifaGeneral = new Tarifa("General", 5000);
    private static Tarifa tarifaVIP = new Tarifa("VIP", 8000);
    private static Tarifa tarifaPalco = new Tarifa("Palco", 10000);

    // Lista de entradas compradas en la última operación
    private static List<Entrada> ultimasEntradasCompradas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializar los asientos como libres
        for (int i = 0; i < CAPACIDAD; i++) {
            asientos[i] = new Asiento(i + 1);
        }

        int opcion = 0;
        do {
            System.out.println("\n=== MENÚ " + NOMBRE_TEATRO + " ===");
            System.out.println("1. Reservar asiento");
            System.out.println("2. Modificar reserva (cambio de asiento)");
            System.out.println("3. Comprar entradas reservadas");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Debe ingresar un número.");
                scanner.nextLine();
                continue;
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> reservarAsiento(scanner);
                case 2 -> modificarReserva(scanner);
                case 3 -> comprarReservas(scanner);
                case 4 -> imprimirBoleta();
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 5);

        scanner.close();
    }

    /**
     * Reserva un asiento libre.
     */
    private static void reservarAsiento(Scanner scanner) {
        System.out.print("Ingrese el número de asiento a reservar (1 al " + CAPACIDAD + "): ");
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Debe ingresar un número.");
            scanner.nextLine();
            return;
        }
        int numero = scanner.nextInt();

        if (numero < 1 || numero > CAPACIDAD) {
            System.out.println("Número inválido.");
            return;
        }

        Asiento asiento = asientos[numero - 1];
        if (asiento.getEstado().equals("libre")) {
            asiento.setEstado("reservado");
            reservados++;
            System.out.println("Asiento " + numero + " reservado con éxito.");
        } else {
            System.out.println("El asiento no está disponible.");
        }

        mostrarMapaAsientos();
    }

    /**
     * Permite modificar una reserva de asiento (cambiar a otro asiento libre).
     */
    private static void modificarReserva(Scanner scanner) {
        System.out.print("Ingrese el número de asiento reservado a modificar: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Debe ingresar un número.");
            scanner.nextLine();
            return;
        }
        int numeroActual = scanner.nextInt();

        if (numeroActual < 1 || numeroActual > CAPACIDAD) {
            System.out.println("Número inválido.");
            return;
        }

        Asiento asientoActual = asientos[numeroActual - 1];
        if (!asientoActual.getEstado().equals("reservado")) {
            System.out.println("Ese asiento no está reservado.");
            return;
        }

        System.out.print("Ingrese el nuevo número de asiento (1 al " + CAPACIDAD + "): ");
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Debe ingresar un número.");
            scanner.nextLine();
            return;
        }
        int nuevoNumero = scanner.nextInt();

        if (nuevoNumero < 1 || nuevoNumero > CAPACIDAD) {
            System.out.println("Número inválido.");
            return;
        }

        Asiento asientoNuevo = asientos[nuevoNumero - 1];
        if (!asientoNuevo.getEstado().equals("libre")) {
            System.out.println("El nuevo asiento no está disponible.");
            return;
        }

        // Cambio de reserva
        asientoActual.setEstado("libre");
        asientoNuevo.setEstado("reservado");
        System.out.println("Reserva movida del asiento " + numeroActual + " al asiento " + nuevoNumero + ".");

        mostrarMapaAsientos();
    }

    /**
     * Compra entradas seleccionando entre los asientos reservados.
     */
    private static void comprarReservas(Scanner scanner) {
        List<Integer> reservadosDisponibles = new ArrayList<>();
        for (Asiento a : asientos) {
            if (a.getEstado().equals("reservado")) reservadosDisponibles.add(a.getNumero());
        }

        if (reservadosDisponibles.isEmpty()) {
            System.out.println("No hay asientos reservados para comprar.");
            return;
        }

        System.out.println("Asientos reservados: " + reservadosDisponibles);
        System.out.print("Ingrese los números de asiento a comprar separados por espacio (ej: 1 3 5): ");
        scanner.nextLine(); // limpiar buffer
        String linea = scanner.nextLine();
        String[] tokens = linea.split(" ");

        List<Integer> seleccionados = new ArrayList<>();
        for (String t : tokens) {
            try {
                int num = Integer.parseInt(t);
                if (reservadosDisponibles.contains(num)) {
                    seleccionados.add(num);
                } else {
                    System.out.println("Asiento " + num + " no está reservado, se ignora.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido: " + t);
            }
        }

        if (seleccionados.isEmpty()) {
            System.out.println("No se seleccionaron asientos válidos.");
            return;
        }

        System.out.println("Seleccione la tarifa:");
        System.out.println("1. " + tarifaGeneral.mostrarTarifa());
        System.out.println("2. " + tarifaVIP.mostrarTarifa());
        System.out.println("3. " + tarifaPalco.mostrarTarifa());
        System.out.print("Opción: ");
        int opcionTarifa = scanner.hasNextInt() ? scanner.nextInt() : 1;

        Tarifa tarifaSeleccionada = switch (opcionTarifa) {
            case 2 -> tarifaVIP;
            case 3 -> tarifaPalco;
            default -> tarifaGeneral;
        };

        ultimasEntradasCompradas.clear();

        for (int num : seleccionados) {
            Asiento a = asientos[num - 1];
            a.setEstado("vendido");
            Entrada entrada = new Entrada(a.getNumero(), tarifaSeleccionada.getPrecio(), "vendida");
            ultimasEntradasCompradas.add(entrada);
            vendidos++;
            reservados--;
            System.out.println("Asiento " + num + " comprado con éxito.");
        }

        mostrarMapaAsientos();
    }

    /**
     * Imprime la boleta de la última compra.
     * Incluye 3 puntos de depuración.
     */
    private static void imprimirBoleta() {
        if (ultimasEntradasCompradas.isEmpty()) {
            System.out.println("No se ha realizado ninguna compra aún.");
            return;
        }

        System.out.println("\n--- BOLETA DE COMPRA ---");
        int total = 0;

        // DEBUG 1: inicio de recorrido de entradas
        System.out.println("[DEBUG] Iniciando impresión de entradas compradas...");

        for (Entrada e : ultimasEntradasCompradas) {
            e.imprimirBoleta();
            // DEBUG 2: acumulando precio
            System.out.println("[DEBUG] Sumando precio del asiento " + e.getNumero() + ": $" + e.getPrecio());
            total += e.getPrecio();
        }

        // DEBUG 3: total calculado
        System.out.println("[DEBUG] Total calculado = $" + total);

        System.out.println("Total a pagar: $" + total);
        System.out.println("-----------------------");
    }

    /**
     * Muestra el mapa de todos los asientos con su estado.
     * 🟩 = Libre | 🟧 = Reservado | 🟥 = Vendido
     */
    private static void mostrarMapaAsientos() {
        System.out.println("\n--- MAPA DE ASIENTOS ---");
        for (Asiento asiento : asientos) {
            String emoji = switch (asiento.getEstado()) {
                case "libre" -> "🟩";
                case "reservado" -> "🟧";
                case "vendido" -> "🟥";
                default -> "?";
            };
            System.out.printf("[%2d%s] ", asiento.getNumero(), emoji);

            if (asiento.getNumero() % 5 == 0) System.out.println();
        }
        System.out.println("\n🟩 Libre | 🟧 Reservado | 🟥 Vendido");
        System.out.println("Asientos reservados: " + reservados + " | Vendidos: " + vendidos +
                " | Libres: " + (CAPACIDAD - reservados - vendidos));
    }
}
