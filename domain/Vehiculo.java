package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Vehiculo implements Serializable{

    //Por si en un futuro se modifica la clase, para poder mantener el control de versiones
    private static final long serialVersionUID = 1L;

    private String patente;
    private String tipo; //Auto - Motocicleta - Camioneta
    private String marca;
    private String modelo;
    private int anioFabricacion;
    private String color;
    private boolean esUsado;
    private boolean tuvoMantenimiento;
    private LocalDate fechaBaja = null;

    public Vehiculo() {
    }

    public Vehiculo(String patente, String tipo, String marca, String modelo, int anioFabricacion, String color, boolean esUsado,
                    boolean tuvoMantenimiento, LocalDate fechaBaja) {
        this.patente = patente;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.color = color;
        this.esUsado = esUsado;
        this.tuvoMantenimiento = tuvoMantenimiento;
        this.fechaBaja = fechaBaja;
    }

    @Override
    public String toString() {
        return "patente: " + patente + "\n" +
                "tipo: " + tipo + "\n" +
                "marca: " + marca + "\n" +
                "modelo: " + modelo + "\n" +
                "anioFabricacion: " + anioFabricacion + "\n" +
                "color: " + color + "\n" +
                "esUsado: " + esUsado + "\n" +
                "tuvoMantenimiento: " + tuvoMantenimiento;
    }

    public void mostrarInfo() {
        System.out.println("Patente del vehiculo: " + this.patente);
        System.out.println("Tipo Vehiculo: " + this.tipo);
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año de fabricación: " + this.anioFabricacion);
        System.out.println("Color: " + this.color);
        System.out.println("Usado: " + (this.esUsado ? "Sí" : "No"));
        System.out.println("Tuvo mantenimiento: " + (this.tuvoMantenimiento ? "Sí" : "No"));
        System.out.println("Fecha de Baja: " + (this.fechaBaja == null ? "Activo" : this.fechaBaja));
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnioFabricacion() {
        return this.anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getEsUsado() {
        return this.esUsado;
    }

    public void setEsUsado(boolean esUsado) {
        this.esUsado = esUsado;
    }

    public boolean getTuvoMantenimiento() {
        return this.tuvoMantenimiento;
    }

    public void setTuvoMantenimiento(boolean tuvoMantenimiento) {
        this.tuvoMantenimiento = tuvoMantenimiento;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Vehiculo v = (Vehiculo) obj;
        return Objects.equals(patente, v.patente);
    }
    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }
}