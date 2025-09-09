package cl.teatromoro.gestionentradas.principal;

import cl.teatromoro.gestionentradas.clasesbasicas.Entrada;
import cl.teatromoro.gestionentradas.clasesbasicas.Tarifa;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal que gestiona el sistema de ventas de entradas
 * para el Teatro Moro.
 *
 * Funcionalidades:
 * - Venta de entradas.
 * - Visualización de promociones.
 * - Búsqueda de entradas por número.
 * - Eliminación de entradas.
 *
 * Variables estáticas:
 * - nombreTeatro: nombre del teatro.
 * - capacidad: cantidad máxima de butacas disponibles.
 * - totalEntradasVendidas: contador de entradas vendidas.
 * - ingresosTotales: acumulador de dinero recaudado.
 *
 * Estructuras aplicadas:
 * - Ciclos iterativos: do-while, for.
 * - Estructuras condicionales: if, switch.
 * - Uso de variables locales, de instancia y estáticas.
 */
public class Main {

    // --- Variables estáticas (globales) ---
    private static String nombreTeatro = "Teatro Moro";
    private static int capacidad = 100;
    private static int totalEntradasVendidas = 0;
    private static int ingresosTotales = 0;

    // --- Lista para almacenar las entradas vendidas ---
    private static ArrayList<Entrada> listaEntradas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Menú principal
        do {
            System.out.println("\n🎭 Bienvenido al " + nombreTeatro);
            System.out.println("Capacidad: " + capacidad + " butacas");
            System.out.println("Entradas vendidas: " + totalEntradasVendidas);
            System.out.println("Ingresos: $" + ingresosTotales);

            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Venta de entradas");
            System.out.println("2. Promociones");
            System.out.println("3. Buscar entrada");
            System.out.println("4. Eliminar entrada");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.hasNextInt() ? sc.nextInt() : 0;
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> venderEntrada(sc);
                case 2 -> mostrarPromociones();
                case 3 -> buscarEntrada(sc);
                case 4 -> eliminarEntrada(sc);
                case 5 -> System.out.println("👋 Gracias por usar el sistema.");
                default -> System.out.println("❌ Opción inválida, intente nuevamente.");
            }

        } while (opcion != 5);

        sc.close();
    }

    /**
     * Opción 1: Venta de entradas.
     * Solicita datos del cliente, aplica descuentos y guarda la entrada.
     */
    private static void venderEntrada(Scanner sc) {
        if (totalEntradasVendidas >= capacidad) {
            System.out.println("❌ No quedan entradas disponibles.");
            return;
        }

        // --- Variables locales ---
        String tipoEntrada;
        String zona;
        int edadCliente;
        int descuentoAplicado = 0;

        // Selección del tipo de entrada
        System.out.println("\nSeleccione el tipo de entrada:");
        System.out.println("1. VIP ($35000)");
        System.out.println("2. Platea baja ($25000)");
        System.out.println("3. Platea alta ($15000)");
        System.out.println("4. Palcos ($11000)");
        int opcionEntrada = sc.nextInt();
        sc.nextLine();

        tipoEntrada = switch (opcionEntrada) {
            case 1 -> "vip";
            case 2 -> "platea baja";
            case 3 -> "platea alta";
            case 4 -> "palcos";
            default -> "general";
        };

        // Selección de zona
        System.out.print("Ingrese zona (A/B/C): ");
        zona = sc.nextLine().toUpperCase();

        // Edad del cliente
        System.out.print("Ingrese su edad: ");
        edadCliente = sc.nextInt();
        sc.nextLine();

        // Tarifa según estudiante o general
        System.out.print("¿Es estudiante? (s/n): ");
        boolean estudiante = sc.nextLine().equalsIgnoreCase("s");
        String tipoTarifa = estudiante ? "estudiante" : "general";

        // Crear objetos
        Tarifa tarifa = new Tarifa(tipoTarifa, edadCliente);
        Entrada entrada = new Entrada(tipoEntrada, tarifa, zona);

        // Calcular precio final y descuentos
        int precioFinal = tarifa.obtenerPrecio(tipoEntrada);
        if (tipoTarifa.equals("estudiante")) descuentoAplicado = 10;
        else if (edadCliente >= 65) descuentoAplicado = 15;

        // Guardar entrada en la lista
        listaEntradas.add(entrada);
        totalEntradasVendidas++;
        ingresosTotales += precioFinal;

        // Resumen de la compra
        System.out.println("\n✅ Entrada vendida con éxito:");
        System.out.println("Número: " + entrada.getNumeroEntrada());
        System.out.println("Tipo: " + tipoEntrada + " | Zona: " + zona);
        System.out.println("Edad: " + edadCliente + " | Tarifa: " + tipoTarifa);
        System.out.println("Descuento aplicado: " + descuentoAplicado + "%");
        System.out.println("Precio final: $" + precioFinal);
    }

    /**
     * Opción 2: Muestra las promociones vigentes.
     */
    private static void mostrarPromociones() {
        System.out.println("\n📢 Promociones disponibles:");
        System.out.println("- 10% de descuento para estudiantes.");
        System.out.println("- 15% de descuento para tercera edad (+65).");
        System.out.println("- 3 entradas VIP por $90.000 (ahorras $15.000).");
    }

    /**
     * Opción 3: Permite buscar entradas por número.
     */
    private static void buscarEntrada(Scanner sc) {
        System.out.print("\n🔎 Ingrese número de entrada a buscar: ");
        int numero = sc.nextInt();

        boolean encontrada = false;
        for (Entrada e : listaEntradas) {
            if (e.getNumeroEntrada() == numero) {
                System.out.println("✅ Entrada encontrada:");
                System.out.println("Número: " + e.getNumeroEntrada());
                System.out.println("Tipo: " + e.getTipoEntrada());
                System.out.println("Zona: " + e.getZona());
                System.out.println("Precio: $" + e.getPrecio());
                System.out.println("Tarifa: " + e.getTarifa().getTipoTarifa());
                encontrada = true;
                break;
            }
        }
        if (!encontrada) System.out.println("❌ No se encontró la entrada.");
    }

    /**
     * Opción 4: Permite eliminar entradas ya compradas.
     */
    private static void eliminarEntrada(Scanner sc) {
        System.out.print("\n🗑️ Ingrese número de entrada a eliminar: ");
        int numero = sc.nextInt();

        for (Entrada e : listaEntradas) {
            if (e.getNumeroEntrada() == numero) {
                listaEntradas.remove(e);
                System.out.println("✅ Entrada eliminada.");
                return;
            }
        }
        System.out.println("❌ No se encontró la entrada.");
    }
}
