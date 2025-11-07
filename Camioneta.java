public class Camioneta extends Vehiculo {

    private String tipoCabina;
    private double capacidadCarga; // en kilogramos
    private String tipoTraccion;   // 4x2, 4x4
    private boolean tieneCajaCubierta;

    public Camioneta() {}

    public Camioneta(String marca, String modelo, int anioFabricacion, String color, boolean esUsado,
                     boolean tuvoMantenimiento, String tipoCabina, double capacidadCarga, String tipoTraccion,
                     boolean tieneCajaCubierta) {
        super(marca, modelo, anioFabricacion, color, esUsado, tuvoMantenimiento);
        this.tipoCabina = tipoCabina;
        this.capacidadCarga = capacidadCarga;
        this.tipoTraccion = tipoTraccion;
        this. tieneCajaCubierta = tieneCajaCubierta;
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
