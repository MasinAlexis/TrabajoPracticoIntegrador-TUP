package service;

import domain.Vehiculo;
import java.time.LocalDate;
import java.util.List;

public class EliminarVehiculo {

    public static List<Vehiculo> bajaLogicaDeVehiculo(List<Vehiculo> listadoVehiculos, String patente) {

        //Manejo una lista nueva porque no vamos a actualizar sobre el parametro de entrada de la funcion
        List<Vehiculo> listadoAActualizar = listadoVehiculos;
        for (Vehiculo vehiculo : listadoAActualizar) {
            vehiculo.mostrarInfo();
        }

        Vehiculo vehiculo = listadoAActualizar.stream()
                .filter(v -> v.getPatente().equals(patente))
                .findFirst()
                .orElse(null);

        if (vehiculo == null) {
            System.out.println("Agregar excepcion para: No existe un vehiculo con ese titular.");
        }
        // Al modificar el objeto, lo estamos modificando dentro del mismo listado
        vehiculo.setFechaBaja(LocalDate.now());

        return listadoAActualizar;
    }

}
