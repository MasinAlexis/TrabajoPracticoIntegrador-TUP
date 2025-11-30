package service;

import domain.Vehiculo;
import excepciones.ExceptionTipoDeVehiculoNoValido;
import excepciones.ExceptionVechiculoNoEncontrado;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ActualizarVehiculo {

    private static Scanner scanner = new Scanner(System.in);

    public static List<Vehiculo> actualizarVehiculo(List<Vehiculo> listadoVehiculos, Integer tipoVehiculo, String patente) throws IOException {

        Vehiculo vehiculo = listadoVehiculos.stream()
                .filter(v -> v.getPatente().equalsIgnoreCase(patente))
                .findFirst()
                .orElse(null);

        if (vehiculo == null) {
            throw new ExceptionVechiculoNoEncontrado("El vehículo con patente " + patente + " no se encuentra cargado. ");
        }

        if (!vehiculo.getTipo().equals(tipoVehiculo(tipoVehiculo))) {
            throw new ExceptionTipoDeVehiculoNoValido("El tipo de vehiculo '" + tipoVehiculo(tipoVehiculo) +
                                                      "' no coincide con el tipo de vehículo para la patente " + patente);
        }

        Vehiculo vehiculoActualizado = new Vehiculo();
        if (vehiculo.getFechaBaja() != null) {
            System.out.print("El vehiculo se encuentra dado de baja, desea darlo de alta para actualizarlo? ");
            String entrada = scanner.nextLine().toUpperCase();
            if (entrada.equals("SI")) {
                switch (tipoVehiculo) {
                    case 1:
                        vehiculoActualizado = InsertarVehiculos.insertarAutomovil();
                        break;
                    case 2:
                        vehiculoActualizado = InsertarVehiculos.insertarCamioneta();
                        break;
                    case 3:
                        vehiculoActualizado = InsertarVehiculos.insertarMotocicleta();
                        break;
                    default:
                        break;
                }
            } else {
                throw new ExceptionVechiculoNoEncontrado("El vehículo con patente: " + patente + " se encuentra dado de baja.");
            }
        } else {
            switch (tipoVehiculo) {
                case 1:
                    vehiculoActualizado = InsertarVehiculos.insertarAutomovil();
                    break;
                case 2:
                    vehiculoActualizado = InsertarVehiculos.insertarCamioneta();
                    break;
                case 3:
                    vehiculoActualizado = InsertarVehiculos.insertarMotocicleta();
                    break;
                default:
                    break;
            }
        }
        // Actualizamos nuestro vehiculo
        int indice = listadoVehiculos.indexOf(vehiculo);
        listadoVehiculos.set(indice, vehiculoActualizado);

        return listadoVehiculos;
    }

    private static String tipoVehiculo(Integer tipoVehiculo) {
        switch (tipoVehiculo) {
            case 1:
                return "Auto";
            case 2:
                return "Camioneta";
            case 3:
                return "Motocicleta";
            default:
                return "No Especificado";
        }
    }
}
