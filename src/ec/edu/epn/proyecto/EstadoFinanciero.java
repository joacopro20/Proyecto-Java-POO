package ec.edu.epn.proyecto;
import java.util.Locale; // Necesario para el formato de salida

public class EstadoFinanciero {
    private Producto[] catalogo; // Arreglo como repositorio de objetos
    private double gananciaTotalNeta;
    private double ingresosBrutos;

    public EstadoFinanciero() {
        // Inicializador del catálogo al crear el EstadoFinanciero
        this.catalogo = new Producto[] {
            new Producto("L01", "Laptop HP", 500.00, 750.00, 10), 
            new Producto("C01", "Celular Samsung", 200.00, 350.00, 20) 
        };
        this.gananciaTotalNeta = 0.0;
        this.ingresosBrutos = 0.0;
    }

    // Método que interactúa con las entidades de transacción
    public boolean registrarVentaExterna(int indiceCatalogo, int cantidad, String numFactura) {
        if (indiceCatalogo >= 0 && indiceCatalogo < catalogo.length) {
            Producto productoSeleccionado = catalogo[indiceCatalogo];
            Venta transaccion = new Venta(numFactura, productoSeleccionado, cantidad);
            
            if (transaccion.procesar()) {
                this.gananciaTotalNeta += transaccion.getGanancia();
                this.ingresosBrutos += (productoSeleccionado.getPrecioVenta() * cantidad);
                return true;
            }
        }
        return false;
    }

    public void mostrarReporteCierre() {
        // Se utiliza printf con Locale.US para asegurar la coma decimal según el estándar de la materia
        System.out.printf(Locale.US, "%n--- ESTADO FINANCIERO DEL NEGOCIO ---%n"); 
        System.out.printf(Locale.US, "Productos instanciados en el sistema: %d%n", Producto.getCreados()); // Llama al método estático
        System.out.printf(Locale.US, "Ingresos Brutos: $%10.2f%n", ingresosBrutos); 
        System.out.printf(Locale.US, "Impuestos (Referencial al %.0f%%): $%10.2f%n", (Producto.IVA * 100), (ingresosBrutos * Producto.IVA)); 
        System.out.printf(Locale.US, "Ganancia Neta Limpia: $%10.2f%n", gananciaTotalNeta); 
    }
}
