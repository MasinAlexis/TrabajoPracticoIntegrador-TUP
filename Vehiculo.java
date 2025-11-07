public class Vehiculo {

    private String marca;
    private String modelo;
    private int anioFabricacion;
    private String color;
    private boolean esUsado;
    private boolean tuvoMantenimiento;

    public Vehiculo() {
    }

    public Vehiculo(String marca, String modelo, int anioFabricacion, String color, boolean esUsado,
            boolean tuvoMantenimiento) {
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.color = color;
        this.esUsado = esUsado;
        this.tuvoMantenimiento = tuvoMantenimiento;
    }

    public void mostrarInfo() {
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año de fabricación: " + this.anioFabricacion);
        System.out.println("Color: " + this.color);
        System.out.println("Usado: " + (this.esUsado ? "Sí" : "No"));
        System.out.println("Tuvo mantenimiento: " + (this.tuvoMantenimiento ? "Sí" : "No"));
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

    public boolean isEsUsado() {
        return this.esUsado;
    }

    public void setEsUsado(boolean esUsado) {
        this.esUsado = esUsado;
    }

    public boolean isTuvoMantenimiento() {
        return this.tuvoMantenimiento;
    }

    public void setTuvoMantenimiento(boolean tuvoMantenimiento) {
        this.tuvoMantenimiento = tuvoMantenimiento;
    }
}
