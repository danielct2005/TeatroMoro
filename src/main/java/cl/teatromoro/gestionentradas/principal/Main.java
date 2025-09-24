package cl.teatromoro.gestionentradas.principal;

import cl.teatromoro.gestionentradas.clasesbasicas.Asiento;
import cl.teatromoro.gestionentradas.clasesbasicas.Entrada;
import cl.teatromoro.gestionentradas.clasesbasicas.Tarifa;
import cl.teatromoro.gestionentradas.clasesbasicas.Venta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String NOMBRE_TEATRO = "Teatro Moro";
    private static final int CAPACIDAD = 10;
    private static Asiento[] asientos = new Asiento[CAPACIDAD];
    private static int vendidos = 0;
    private static int reservados = 0;

    // Tarifas
    private static Tarifa tarifaGeneral = new Tarifa("General", 5000);
    private static Tarifa tarifaVIP = new Tarifa("VIP", 8000);
    private static Tarifa tarifaPalco = new Tarifa("Palco", 10000);

    // Última compra
    private static List<Venta> ultimasVentas = new ArrayList<>();

    // Estadísticas globales
    private static int totalEntradasVendidas = 0;
    private static int ingresosTotales = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializar asientos
        for (int i = 0; i < CAPACIDAD; i++) {
            asientos[i] = new Asiento(i + 1);
        }

        int opcion = 0;
        do {
            System.out.println("\n=== MENÚ " + NOMBRE_TEATRO + " ===");
            System.out.println("1. Reservar asiento");
            System.out.println("2. Modificar reserva (cambio de asiento)");
            System.out.println("3. Comprar entradas reservadas");
            System.out.println("4. Imprimir boleta de última compra");
            System.out.println("5. Ver ingresos totales");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida.");
                scanner.nextLine();
                continue;
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> reservarAsiento(scanner);
                case 2 -> modificarReserva(scanner);
                case 3 -> comprarReservas(scanner);
                case 4 -> imprimirBoleta();
                case 5 -> verIngresosTotales();
                case 6 -> System.out.println("Gracias por su compra. ¡Vuelva pronto!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void reservarAsiento(Scanner scanner) {
        System.out.print("Ingrese número de asiento a reservar (1-" + CAPACIDAD + "): ");
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

    private static void modificarReserva(Scanner scanner) {
        System.out.print("Ingrese número de asiento reservado: ");
        int actual = scanner.nextInt();

        if (actual < 1 || actual > CAPACIDAD) return;

        Asiento asientoActual = asientos[actual - 1];
        if (!asientoActual.getEstado().equals("reservado")) {
            System.out.println("Ese asiento no está reservado.");
            return;
        }

        System.out.print("Ingrese nuevo número de asiento: ");
        int nuevo = scanner.nextInt();

        if (nuevo < 1 || nuevo > CAPACIDAD) return;

        Asiento asientoNuevo = asientos[nuevo - 1];
        if (!asientoNuevo.getEstado().equals("libre")) {
            System.out.println("El asiento no está disponible.");
            return;
        }

        asientoActual.setEstado("libre");
        asientoNuevo.setEstado("reservado");
        System.out.println("Reserva cambiada al asiento " + nuevo);
        mostrarMapaAsientos();
    }

    private static void comprarReservas(Scanner scanner) {
        List<Integer> reservadosDisponibles = new ArrayList<>();
        for (Asiento a : asientos) {
            if (a.getEstado().equals("reservado")) reservadosDisponibles.add(a.getNumero());
        }

        if (reservadosDisponibles.isEmpty()) {
            System.out.println("No hay asientos reservados.");
            return;
        }

        System.out.println("Asientos reservados: " + reservadosDisponibles);
        System.out.print("Ingrese los números de asiento a comprar (ej: 1 2 3): ");
        scanner.nextLine();
        String[] tokens = scanner.nextLine().split(" ");

        ultimasVentas.clear();

        for (String t : tokens) {
            try {
                int num = Integer.parseInt(t);
                if (reservadosDisponibles.contains(num)) {
                    Asiento a = asientos[num - 1];

                    int opcionTarifa;
                    do {
                        System.out.println("Seleccione tarifa para asiento " + num + ":");
                        System.out.println("1. " + tarifaGeneral.mostrarTarifa());
                        System.out.println("2. " + tarifaVIP.mostrarTarifa());
                        System.out.println("3. " + tarifaPalco.mostrarTarifa());
                        opcionTarifa = scanner.nextInt();
                        if (opcionTarifa < 1 || opcionTarifa > 3) {
                            System.out.println("⚠️ Opción inválida. Intente de nuevo.");
                        }
                    } while (opcionTarifa < 1 || opcionTarifa > 3);

                    Tarifa tarifa = switch (opcionTarifa) {
                        case 2 -> tarifaVIP;
                        case 3 -> tarifaPalco;
                        default -> tarifaGeneral;
                    };

                    int tipoCliente;
                    do {
                        System.out.print("¿Cliente? 1=Estudiante, 2=Tercera edad, 0=Ninguno: ");
                        tipoCliente = scanner.nextInt();
                        if (tipoCliente != 0 && tipoCliente != 1 && tipoCliente != 2) {
                            System.out.println("⚠️ Opción inválida. Intente de nuevo.");
                        }
                    } while (tipoCliente != 0 && tipoCliente != 1 && tipoCliente != 2);

                    double descuento = switch (tipoCliente) {
                        case 1 -> 0.10; // estudiante
                        case 2 -> 0.15; // tercera edad
                        default -> 0.0; // ninguno
                    };

                    double precioFinal = tarifa.getPrecio() - (tarifa.getPrecio() * descuento);

                    // Crear venta
                    a.setEstado("vendido");
                    vendidos++;
                    reservados--;
                    Venta venta = new Venta(a, tarifa, descuento, precioFinal);
                    ultimasVentas.add(venta);

                    totalEntradasVendidas++;
                    ingresosTotales += precioFinal;

                    System.out.println("Asiento " + num + " comprado con éxito.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido: " + t);
            }
        }

        mostrarMapaAsientos();
    }


    private static void imprimirBoleta() {
        if (ultimasVentas.isEmpty()) {
            System.out.println("No se ha realizado ninguna compra aún.");
            return;
        }

        System.out.println("--------------------------------------------");
        System.out.println("                 Teatro Moro");
        System.out.println("--------------------------------------------");

        int cantidad = ultimasVentas.size();
        System.out.println("Cantidad: " + cantidad);

        // Ubicaciones
        StringBuilder ubicaciones = new StringBuilder();
        StringBuilder costosBase = new StringBuilder();
        StringBuilder descuentos = new StringBuilder();
        double totalFinal = 0;

        for (Venta v : ultimasVentas) {
            ubicaciones.append(v.getTarifa().getTipo()).append(" - ");
            costosBase.append("$").append(v.getTarifa().getPrecio()).append(" - ");
            descuentos.append((int)(v.getDescuento() * 100)).append("% - ");
            totalFinal += v.getPrecioFinal();
        }

        // Eliminar el último " - "
        if (ubicaciones.length() > 3) ubicaciones.setLength(ubicaciones.length() - 3);
        if (costosBase.length() > 3) costosBase.setLength(costosBase.length() - 3);
        if (descuentos.length() > 3) descuentos.setLength(descuentos.length() - 3);

        System.out.println("Ubicaciones: " + ubicaciones);
        System.out.println("Costos Base: " + costosBase);
        System.out.println("Descuentos Aplicados: " + descuentos);
        System.out.println("Costo Final: $" + (int)totalFinal);

        System.out.println("--------------------------------------------");
        System.out.println("     Gracias por su visita al Teatro Moro");
        System.out.println("--------------------------------------------");
    }


    private static void verIngresosTotales() {
        System.out.println("\n--- INGRESOS TOTALES ---");
        System.out.println("Entradas vendidas: " + totalEntradasVendidas);
        System.out.println("Ingresos acumulados: $" + ingresosTotales);
    }

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
        System.out.println("🟩 Libre | 🟧 Reservado | 🟥 Vendido");
        System.out.println("Reservados: " + reservados + " | Vendidos: " + vendidos +
                " | Libres: " + (CAPACIDAD - reservados - vendidos));
    }
}
