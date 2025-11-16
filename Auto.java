
public class Auto extends Vehiculo {

    private String carroceria;
    private int cantidadPuertas;
    private String tipoCombustible;
    private String transmision;

    public Auto() {
    }

    public Auto(Long dniTitular, String tipo, String marca, String modelo, int anioFabricacion, String color, boolean esUsado,
                boolean tuvoMantenimiento, String carroceria, int cantidadPuertas, String tipoCombustible,
                String transmision) {
        super(dniTitular, tipo, marca, modelo, anioFabricacion, color, esUsado, tuvoMantenimiento);
        this.carroceria = carroceria;
        this.cantidadPuertas = cantidadPuertas;
        this.tipoCombustible = tipoCombustible;
        this.transmision = transmision;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "carroceria=" + this.carroceria + "\n" +
                "puertas=" + this.cantidadPuertas + "\n" +
                "tipoCombustible=" + this.tipoCombustible + "\n" +
                "transmision=" + this.transmision;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Carrocería: " + this.carroceria);
        System.out.println("Cantidad de puertas: " + this.cantidadPuertas);
        System.out.println("Tipo de Combustible: " + this.tipoCombustible);
        System.out.println("Tipo de Transmision: " + this.transmision);
    }

    public String getCarroceria() {
        return this.carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public int getCantidadPuertas() {
        return this.cantidadPuertas;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }

    public String getTipoCombustible() {
        return this.tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getTransmision() {
        return this.transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }
}
