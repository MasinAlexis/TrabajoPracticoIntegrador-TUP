import service.SistemaConcesionariaVehiculos;

import java.io.IOException;

public class GestionDeVehiculos {

    public static void main(String[] args) throws IOException {
        SistemaConcesionariaVehiculos gestion = new SistemaConcesionariaVehiculos();
        // CARGAR VEHÍCULOS PREVIOS
        gestion.obtenerVehiculosFile();
        gestion.iniciarSistema();
    }
}