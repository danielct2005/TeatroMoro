package cl.teatromoro.gestionentradas.clasesbasicas;

public class Venta {
    private Asiento asiento;
    private Tarifa tarifa;
    private double descuento;
    private double precioFinal;

    public Venta(Asiento asiento, Tarifa tarifa, double descuento, double precioFinal) {
        this.asiento = asiento;
        this.tarifa = tarifa;
        this.descuento = descuento;
        this.precioFinal = precioFinal;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    @Override
    public String toString() {
        return "Asiento " + asiento.getNumero() +
                " | Ubicación: " + tarifa.getTipo() +
                " | Base: $" + tarifa.getPrecio() +
                " | Desc: " + (int)(descuento * 100) + "%" +
                " | Final: $" + precioFinal;
    }
}
