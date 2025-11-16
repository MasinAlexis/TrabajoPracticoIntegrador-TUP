import java.math.BigInteger;

public class Motocicleta extends Vehiculo {

    private String tipoMoto; // deportiva, naked, scooter, touring, enduro
    private Integer cilindrada; // en cc
    private String tipoMotor; // 2 tiempos, 4 tiempos
    private boolean tieneBaul;

    public Motocicleta() {
    }

    public Motocicleta(Long dniTitular, String tipo, String marca, String modelo, int anioFabricacion, String color, boolean esUsado,
                       boolean tuvoMantenimiento, String tipoMoto, Integer cilindrada, String tipoMotor, boolean tieneBaul) {
        super(dniTitular, tipo, marca, modelo, anioFabricacion, color, esUsado, tuvoMantenimiento);
        this.tipoMoto = tipoMoto;
        this.cilindrada = cilindrada;
        this.tipoMotor = tipoMotor;
        this.tieneBaul = tieneBaul;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "tipoMoto=" + this.tipoMoto+ "\n" +
                "cilindrada=" + this.cilindrada+ "\n" +
                "tipoMotor=" + this.tipoMotor+ "\n" +
                "tieneBaul=" + this.tieneBaul;
    }
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Tipo de moto: " + this.tipoMoto);
        System.out.println("Cilindrada: " + this.cilindrada + "cc");
        System.out.println("Tipo de motor: " + this.tipoMotor);
        System.out.println("Tiene bahúl: " + (this.tieneBaul == true ? "Si" : "No"));
    }

    public String getTipoMoto() {
        return this.tipoMoto;
    }

    public void setTipoMoto(String tipoMoto) {
        this.tipoMoto = tipoMoto;
    }

    public Integer getCilindrada() {
        return this.cilindrada;
    }

    public void setCilindrada(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getTipoMotor() {
        return this.tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public boolean isTieneBaul() {
        return this.tieneBaul;
    }

    public void setTieneBaul(boolean tieneBaul) {
        this.tieneBaul = tieneBaul;
    }
}
