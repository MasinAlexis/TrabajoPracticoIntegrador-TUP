package service;

import domain.Auto;
import domain.Motocicleta;
import domain.Camioneta;
import enums.OpcionSiNo;
import enums.TipoCombustible;
import excepciones.ExceptionParametrosEntrada;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class InsertarVehiculos {

    static SistemaConcesionariaVehiculos gestionVehiculos = new SistemaConcesionariaVehiculos();
    private static Scanner entrada = new Scanner(System.in);

    public static Motocicleta insertarMotocicleta() throws IOException {

        System.out.println("----- Ingrese los datos de la Moto -----");

        System.out.print("¿Es usado?(Si/No): ");
        boolean esUsado = OpcionSiNo.valueOf(entrada.nextLine().toUpperCase()).equals(OpcionSiNo.SI);

        String patente = "";
        if (!esUsado) {
            patente = "TD-0" + gestionVehiculos.obtenerNumeroCeroKilometro();
        } else {
            System.out.print("Patente: ");
            patente = entrada.nextLine().toUpperCase();
            if(!patente.matches("^[0-9]{3}[A-Z]{3}$") && !patente.matches("^[A-Z]{1}[0-9]{3}[A-Z]{3}$")) {
                throw new ExceptionParametrosEntrada("La patente: " + patente + " para la motocicleta no tiene el formato correcto.\n" +
                        "Formatos aceptados: '123ABC' o 'A123BCD'");
            }
            if(gestionVehiculos.validarExistenciaVehiculo(patente)) {
                throw new ExceptionParametrosEntrada("La motocicleta con patente: " + patente + " ya se encuentra guardada.");
            }
        }

        System.out.print("Marca: ");
        String marca = entrada.nextLine().toUpperCase();

        System.out.print("Modelo: ");
        String modelo = entrada.nextLine().toUpperCase();

        System.out.print("Año de fabricación: ");
        Integer anioFabricacion = Integer.parseInt(entrada.nextLine());
        if (anioFabricacion > LocalDate.now().getYear()) {
            throw new ExceptionParametrosEntrada("El año de fabricacion no puede ser mayor al año actual.");
        }

        System.out.print("Color: ");
        String color = entrada.nextLine().toUpperCase();

        System.out.print("¿Tuvo mantenimiento? (Si/No): ");
        boolean tuvoMantenimiento = OpcionSiNo.valueOf(entrada.nextLine().toUpperCase()).equals(OpcionSiNo.SI);

        System.out.print("Tipo de moto (ej: Enduro, Naked, etc): ");
        String tipoMoto = entrada.nextLine().toUpperCase();

        System.out.print("Cilindrada: ");
        Integer cilindrada = Integer.parseInt(entrada.nextLine());

        System.out.print("Tipo de motor (ej: 4T, 2T): ");
        String tipoMotor = entrada.nextLine().toUpperCase();

        System.out.print("¿Tiene baúl? (Si/No): ");
        boolean tieneBaul = OpcionSiNo.valueOf(entrada.nextLine().toUpperCase()).equals(OpcionSiNo.SI);

        // Crear la instancia
        Motocicleta motocicleta = new Motocicleta(patente, "Motocicleta", marca, modelo, anioFabricacion,
                color, esUsado, tuvoMantenimiento, null,  tipoMoto, cilindrada, tipoMotor, tieneBaul);

        System.out.println(">>> Motocicleta cargada correctamente <<<");
        if (!esUsado) {
            System.out.println("Su vehículo es 0KM, se le asignó la siguiente patente temporal: " + patente);
        }

        return motocicleta;
    }

    public static Auto insertarAutomovil() throws IOException {
        System.out.println("----- Ingrese los datos del Auto -----");

        System.out.print("¿Es usado?(Si/No): ");
        boolean esUsado = OpcionSiNo.valueOf(entrada.nextLine().toUpperCase()).equals(OpcionSiNo.SI);

        String patente = "";
        if (!esUsado) {
            patente = "TD-0" + gestionVehiculos.obtenerNumeroCeroKilometro();
        } else {
            System.out.print("Patente: ");
            patente = entrada.nextLine().toUpperCase();
            if(!patente.matches("^[A-Z]{3}[0-9]{3}$") && !patente.matches("^[A-Z]{2}[0-9]{3}[A-Z]{2}$")) {
                throw new ExceptionParametrosEntrada("La patente: " + patente + " para el auto no tiene el formato correcto.\n" +
                        "Formatos aceptados: 'ABC123' o 'AB123CD'");
            }
            if(gestionVehiculos.validarExistenciaVehiculo(patente)) {
                throw new ExceptionParametrosEntrada("El auto con patente: " + patente + " ya se encuentra guardado.");
            }
        }

        System.out.print("Marca: ");
        String marca = entrada.nextLine().toUpperCase();

        System.out.print("Modelo: ");
        String modelo = entrada.nextLine().toUpperCase();

        System.out.print("Año de fabricación: ");
        Integer anioFabricacion = Integer.parseInt(entrada.nextLine());
        if (anioFabricacion > LocalDate.now().getYear()) {
            throw new ExceptionParametrosEntrada("El año de fabricacion no puede ser mayor al año actual.");
        }

        System.out.print("Color: ");
        String color = entrada.nextLine().toUpperCase();

        System.out.print("¿Tuvo mantenimiento? (Si/No): ");
        boolean tuvoMantenimiento = OpcionSiNo.valueOf(entrada.nextLine().toUpperCase()).equals(OpcionSiNo.SI);

        System.out.print("Tipo de Carroceria (ej: Sedan, Coupé, Familiar, etc): ");
        String carroceria = entrada.nextLine().toUpperCase();

        System.out.print("Cantidad de Puertas: ");
        Integer cantidadDePuertas = Integer.parseInt(entrada.nextLine());

        System.out.print("Tipo de combustible (ej: Nafta, Gasoil, etc): ");
        String tipoCombustible = TipoCombustible.valueOf(entrada.nextLine().toUpperCase()).toString();

        System.out.print("Tipo Transmision (Manual, Automatica): ");
        String transmision = entrada.nextLine().toUpperCase();

        // Crear la instancia
        Auto nuevoAuto = new Auto(patente, "Auto", marca, modelo, anioFabricacion,
                color, esUsado, tuvoMantenimiento, null, carroceria, cantidadDePuertas, tipoCombustible, transmision);

        System.out.println(">>> Auto cargado correctamente <<<");

        return nuevoAuto;
    }

    public static Camioneta insertarCamioneta() throws IOException {
        System.out.println("----- Ingrese los datos de la Camioneta -----");

        System.out.print("¿Es usado?(Si/No): ");
        boolean esUsado = OpcionSiNo.valueOf(entrada.nextLine().toUpperCase()).equals(OpcionSiNo.SI);

        String patente = "";
        if (!esUsado) {
            patente = "TD-0" + gestionVehiculos.obtenerNumeroCeroKilometro();
        } else {
            System.out.print("Patente: ");
            patente = entrada.nextLine().toUpperCase();
            if(!patente.matches("^[A-Z]{3}[0-9]{3}$") && !patente.matches("^[A-Z]{2}[0-9]{3}[A-Z]{2}$")) {
                throw new ExceptionParametrosEntrada("La patente: " + patente + " para el auto no tiene el formato correcto.\n" +
                        "Formatos aceptados: 'ABC123' o 'AB123CD'");
            }
            if(gestionVehiculos.validarExistenciaVehiculo(patente)) {
                throw new ExceptionParametrosEntrada("La camioneta con patente: " + patente + " ya se encuentra guardada.");
            }
        }

        System.out.print("Marca: ");
        String marca = entrada.nextLine().toUpperCase();

        System.out.print("Modelo: ");
        String modelo = entrada.nextLine().toUpperCase();

        System.out.print("Año de fabricación: ");
        Integer anioFabricacion = Integer.parseInt(entrada.nextLine());
        if (anioFabricacion > LocalDate.now().getYear()) {
            throw new ExceptionParametrosEntrada("El año de fabricacion no puede ser mayor al año actual.");
        }

        System.out.print("Color: ");
        String color = entrada.nextLine().toUpperCase();

        System.out.print("¿Tuvo mantenimiento? (Si/No): ");
        boolean tuvoMantenimiento = OpcionSiNo.valueOf(entrada.nextLine().toUpperCase()).equals(OpcionSiNo.SI);

        System.out.print("Tipo de Cabina (ej: Simple, Doble, etc): ");
        String tipoCabina = entrada.nextLine().toUpperCase();

        System.out.print("Capacidad de Carga (Litros): ");
        Integer capacidadDeCarga = Integer.parseInt(entrada.nextLine());

        System.out.print("Tipo de Traccion (ej: 4x2, 4x4): ");
        String tipoTraccion = entrada.nextLine().toUpperCase();

        System.out.print("Tiene Caja Cubierta (Si, No): ");
        Boolean tieneCajaCubierta = OpcionSiNo.valueOf(entrada.nextLine().toUpperCase()).equals(OpcionSiNo.SI);

        // Crear la instancia
        Camioneta nuevaCamioneta = new Camioneta (patente, "Camioneta", marca, modelo, anioFabricacion, color,
                esUsado, tuvoMantenimiento, null, tipoCabina, capacidadDeCarga, tipoTraccion, tieneCajaCubierta);

        System.out.println(">>> Camioneta cargado correctamente <<<");

        return nuevaCamioneta;
    }
}
