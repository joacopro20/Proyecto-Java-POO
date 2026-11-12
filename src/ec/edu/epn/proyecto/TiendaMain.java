package ec.edu.epn.proyecto;

import java.util.Scanner;
import java.util.Locale;

public class TiendaMain {

    public static void main(String[] args) {
        // Inicializamos el gestor principal del inventario
        EstadoFinanciero estado = new EstadoFinanciero();
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // Configura el Scanner para leer decimales con punto
        int opcion = 0;

        System.out.println("=== SISTEMA DE INVENTARIO Y UTILIDADES ===");

        // Ciclo iterativo del menú (Entrada -> Proceso -> Salida)[cite: 1, 2]
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Mostrar estado financiero y catálogo");
            System.out.println("2. Registrar una venta");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            
            // Validación básica de entrada[cite: 1]
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
                
                switch (opcion) {
                    case 1:
                        // Salida formateada utilizando el método de la clase EstadoFinanciero[cite: 1]
                        estado.mostrarReporteCierre();
                        break;
                    case 2:
                        System.out.println("\n--- REGISTRO DE VENTA ---");
                        System.out.print("Ingrese el índice del producto en catálogo (ej. 0 para el primero): ");
                        int indice = sc.nextInt();
                        System.out.print("Ingrese la cantidad a vender: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine(); // Limpiar el buffer
                        System.out.print("Ingrese el número de factura: ");
                        String factura = sc.nextLine();

                        // Procesamiento encapsulado: El método valida y retorna boolean
                        boolean exito = estado.registrarVentaExterna(indice, cantidad, factura);
                        
                        if (exito) {
                            System.out.println("Venta procesada y utilidades registradas con éxito.");
                        } else {
                            System.err.println("Error: Venta fallida (verifique índice o stock).");
                        }
                        break;
                    case 3:
                        System.out.println("Cerrando el sistema. ¡Buen día!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Entrada inválida. Ingrese un número.");
                sc.next(); // Descartar la entrada incorrecta
            }
        } while (opcion != 3);

        sc.close();
    }
}
