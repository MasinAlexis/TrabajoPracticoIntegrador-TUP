import java.io.IOException;

public class GestionDeVehiculos {

    public static void main(String[] args) throws IOException {
        SistemaConcesionariaVehiculos gestion = new SistemaConcesionariaVehiculos();

        // Creamos algunos vehículos
        Auto auto = new Auto(37828958L, "Automovil", "Toyota", "Corolla", 2016, "Gris", true, false, "SUV", 4, "Nafta", "Manual");
        Motocicleta moto = new Motocicleta(16722315L, "Motocicleta", "Yamaha", "FZ", 2022, "Negra", false, true, "Carretera", 250, "4 Tiempos", true);
        Camioneta camioneta = new Camioneta(40361162L, "Camioneta", "Volkswagen", "Amarok", 2021, "Blanca", true, true, "Doble", 400, "4x4", true);

        System.out.println("Guardando vehículos...");

        gestion.agregarVehiculo(auto);
        gestion.agregarVehiculo(moto);
        gestion.agregarVehiculo(camioneta);

        System.out.println("Listo! Revisa el archivo txt.");
    }
}