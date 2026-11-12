package ec.edu.epn.proyecto;
public class Venta {
    private final String numeroFactura; // {readOnly} -> 
    private Producto producto;
    private int cantidadVendida;
    private double gananciaGenerada;

    public Venta(String numeroFactura, Producto producto, int cantidadVendida) {
        this.numeroFactura = numeroFactura;
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
        this.gananciaGenerada = 0.0;
    }

    // Botón de acción: Solicita al producto que se venda a sí mismo
    public boolean procesar() {
        // Enviar un mensaje al objeto: Se le pide al producto que valide y ejecute la venta
        if (producto.vender(cantidadVendida)) {
            // Si el producto devuelve true (venta exitosa), se calcula la utilidad
            this.gananciaGenerada = (producto.getPrecioVenta() - producto.getPrecioCompra()) * cantidadVendida;
            return true;
        }
        return false;
    }

    public double getGanancia() {
        return gananciaGenerada;
    }
}
