package service;

import domain.Vehiculo;
import excepciones.ExceptionVechiculoNoEncontrado;

import java.time.LocalDate;
import java.util.List;

public class EliminarVehiculo {

    public static List<Vehiculo> bajaLogicaDeVehiculo(List<Vehiculo> listadoVehiculos, String patente) {

        //Manejo una lista nueva porque no vamos a actualizar sobre el parametro de entrada de la funcion
        List<Vehiculo> listadoAActualizar = listadoVehiculos;

        Vehiculo vehiculo = listadoAActualizar.stream()
                .filter(v -> v.getPatente().equalsIgnoreCase(patente))
                .findFirst()
                .orElse(null);

        if (vehiculo == null) {
            throw new ExceptionVechiculoNoEncontrado("El vehículo con patente: " + patente + " no se encuentra cargado.");
        }
        // Al modificar el objeto, lo estamos modificando dentro del mismo listado
        vehiculo.setFechaBaja(LocalDate.now());

        return listadoAActualizar;
    }

}
