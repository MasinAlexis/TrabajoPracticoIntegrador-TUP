package domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Camioneta extends Vehiculo implements Serializable {

    // Por si en un futuro se modifica la clase, para poder mantener el control de
    // versiones
    private static final long serialVersionUID = 1L;

    private String tipoCabina;
    private double capacidadCarga; // en kilogramos
    private String tipoTraccion; // 4x2, 4x4
    private boolean tieneCajaCubierta;

    public Camioneta() {
    }

    public Camioneta(String patente, String tipo, String marca, String modelo, int anioFabricacion, String color,
            boolean esUsado,
            boolean tuvoMantenimiento, LocalDate fechaBaja, String tipoCabina, double capacidadCarga, String tipoTraccion,
            boolean tieneCajaCubierta) {
        super(patente, tipo, marca, modelo, anioFabricacion, color, esUsado, tuvoMantenimiento, fechaBaja);
        this.tipoCabina = tipoCabina;
        this.capacidadCarga = capacidadCarga;
        this.tipoTraccion = tipoTraccion;
        this.tieneCajaCubierta = tieneCajaCubierta;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "tipoCabina=" + this.tipoCabina + "\n" +
                "capacidadCarga=" + this.capacidadCarga + "\n" +
                "tipoTraccion=" + this.tipoTraccion + "\n" +
                "tieneCajaCubierta=" + this.tieneCajaCubierta;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Tipo de cabina: " + this.tipoCabina);
        System.out.println("Capacidad de carga: " + this.capacidadCarga + "KG");
        System.out.println("Tipo de tracción: " + this.tipoTraccion);
        System.out.println("Tiene caja cubierta: " + (this.tieneCajaCubierta == true ? "Si" : "No"));
    }

    public String getTipoCabina() {
        return this.tipoCabina;
    }

    public void setTipoCabina(String tipoCabina) {
        this.tipoCabina = tipoCabina;
    }

    public double getCapacidadCarga() {
        return this.capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public String getTipoTraccion() {
        return this.tipoTraccion;
    }

    public void setTipoTraccion(String tipoTraccion) {
        this.tipoTraccion = tipoTraccion;
    }

    public boolean isTieneCajaCubierta() {
        return this.tieneCajaCubierta;
    }

    public void setTieneCajaCubierta(boolean tieneCajaCubierta) {
        this.tieneCajaCubierta = tieneCajaCubierta;
    }
}
