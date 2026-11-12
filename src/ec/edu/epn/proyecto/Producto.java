package ec.edu.epn.proyecto;

public class Producto {
    // Miembros estáticos (Subrayados en UML): Pertenecen a la clase
    public static final double IVA = 0.15; // {readOnly} -> Constante para todo el proyecto
    private static int creados = 0; // Contador global de objetos instanciados

    // Atributos de instancia (Estado del objeto protegido con private)
    private final String codigo; // {readOnly} -> final: Se asigna una vez y no cambia
    private final String nombre; // {readOnly}
    private double precioCompra;
    private double precioVenta;
    private int stock;

    // "El constructor tiene la última palabra" para crear el objeto
    public Producto(String codigo, String nombre, double precioCompra, double precioVenta, int stock) {
        // Si nace inválido, se lanza la excepción y se aborta la creación
        if (precioCompra <= 0 || precioVenta <= 0 || stock < 0 || precioCompra >= precioVenta) {
            throw new IllegalArgumentException("Error: Precios y stock deben ser válidos."); 
        }
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        creados++; // Suma 1 a la variable compartida de la clase
    }

    // Getters: "leer sin tocar"
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecioCompra() { return precioCompra; }
    public double getPrecioVenta() { return precioVenta; }
    public int getStock() { return stock; }
    public static int getCreados() { return creados; } 

    // Modificador: El objeto valida y decide si acepta el mensaje
    public boolean vender(int cantidad) {
        // "Los pedidos inválidos no cambian nada"
        if (cantidad <= 0 || cantidad > stock) {
            return false; // Rechaza el cambio y mantiene el invariante intacto[cite: 6]
        }
        stock -= cantidad; // Actualiza el estado
        return true; 
    }
}

