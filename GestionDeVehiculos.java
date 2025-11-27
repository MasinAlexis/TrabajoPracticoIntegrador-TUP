import service.SistemaConcesionariaVehiculos;

import java.io.IOException;

public class GestionDeVehiculos {

    public static void main(String[] args) throws IOException {
        SistemaConcesionariaVehiculos gestion = new SistemaConcesionariaVehiculos();
        // CARGAR VEHÍCULOS PREVIOS
        gestion.obtenerVehiculosFile();
        gestion.iniciarSistema();





























        /*
        // Creamos algunos vehículos
        domain.Auto auto = new domain.Auto(37828958L, "Automovil", "Toyota", "Corolla", 2016, "Gris", true, false, "SUV", 4, "Nafta", "Manual");
        domain.Motocicleta moto = new domain.Motocicleta(16722315L, "domain.Motocicleta", "Yamaha", "FZ", 2022, "Negra", false, true, "Carretera", 250, "4 Tiempos", true);
        domain.Camioneta camioneta = new domain.Camioneta(40361162L, "domain.Camioneta", "Volkswagen", "Amarok", 2021, "Blanca", true, true, "Doble", 400, "4x4", true);

        System.out.println("Guardando vehículos...");

        gestion.agregarVehiculo(auto);
        gestion.agregarVehiculo(moto);
        gestion.agregarVehiculo(camioneta);

        System.out.println("Listo! Revisa el archivo txt.");

        gestion.guardarVehiculosFile();
        gestion.obtenerVehiculosFile();
        gestion.mostrarListadoVehiculos();

        System.out.println("Listado Actual ---------------------------------------------------------------");
        gestion.eliminarVehiculoByDni(37828958L);
        System.out.println("Eliminado ---------------------------------------------------------------");
        gestion.vehiculosDadosDeBaja();
        System.out.println("Listado Actualizado ---------------------------------------------------------------");
        gestion.mostrarListadoVehiculos();
        */
    }
}