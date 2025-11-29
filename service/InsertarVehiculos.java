package service;

import domain.Auto;
import domain.Motocicleta;
import domain.Camioneta;

import java.util.Scanner;

public class InsertarVehiculos {

    private static Scanner entrada = new Scanner(System.in);

    public static Motocicleta insertarMotocicleta() {

        System.out.println("----- Ingrese los datos de la Moto -----");
        Motocicleta motocicleta = new Motocicleta();

        System.out.print("Patente: ");
        String patente = entrada.nextLine().toUpperCase();

        System.out.print("Marca: ");
        String marca = entrada.nextLine().toUpperCase();

        System.out.print("Modelo: ");
        String modelo = entrada.nextLine().toUpperCase();

        System.out.print("Año de fabricación: ");
        Integer anioFabricacion = Integer.parseInt(entrada.nextLine());

        System.out.print("Color: ");
        String color = entrada.nextLine().toUpperCase();

        System.out.print("¿Es usado?(Si/No): ");
        boolean esUsado = entrada.nextLine().equalsIgnoreCase("SI");;

        System.out.print("¿Tuvo mantenimiento? (Si/No): ");
        boolean tuvoMantenimiento = entrada.nextLine().equalsIgnoreCase("SI");;

        System.out.print("Tipo de moto (ej: Enduro, Naked, etc): ");
        String tipoMoto = entrada.nextLine().toUpperCase();

        System.out.print("Cilindrada: ");
        Integer cilindrada = Integer.parseInt(entrada.nextLine());

        System.out.print("Tipo de motor (ej: 4T, 2T): ");
        String tipoMotor = entrada.nextLine().toUpperCase();

        System.out.print("¿Tiene baúl? (Si/No): ");
        boolean tieneBaul = (entrada.nextLine()).equalsIgnoreCase("SI");

        // Crear la instancia
        motocicleta = new Motocicleta(patente, "Motocicleta", marca, modelo, anioFabricacion,
                color, esUsado, tuvoMantenimiento, null,  tipoMoto, cilindrada, tipoMotor, tieneBaul);

        System.out.println(">>> Motocicleta cargada correctamente <<<");
        return motocicleta;
    }

    public static Auto insertarAutomovil() {
        System.out.println("----- Ingrese los datos del Auto -----");

        System.out.print("Patente: ");
        String patente = entrada.nextLine().toUpperCase();

        System.out.print("Marca: ");
        String marca = entrada.nextLine().toUpperCase();

        System.out.print("Modelo: ");
        String modelo = entrada.nextLine().toUpperCase();

        System.out.print("Año de fabricación: ");
        Integer anioFabricacion = Integer.parseInt(entrada.nextLine());

        System.out.print("Color: ");
        String color = entrada.nextLine().toUpperCase();

        System.out.print("¿Es usado?(Si/No): ");
        boolean esUsado = entrada.nextLine().equalsIgnoreCase("SI");;

        System.out.print("¿Tuvo mantenimiento? (Si/No): ");
        boolean tuvoMantenimiento = entrada.nextLine().equalsIgnoreCase("SI");;

        System.out.print("Tipo de Carroceria (ej: Sedan, Coupé, Familiar, etc): ");
        String carroceria = entrada.nextLine().toUpperCase();

        System.out.print("Cantidad de Puertas: ");
        Integer cantidadDePuertas = Integer.parseInt(entrada.nextLine());

        System.out.print("Tipo de combustible (ej: Nafta, Gasoil, etc): ");
        String tipoCombustible = entrada.nextLine().toUpperCase();

        System.out.print("Tipo Transmision (Manual, Automatica): ");
        String transmision = entrada.nextLine().toUpperCase();

        // Crear la instancia
        Auto nuevoAuto = new Auto(patente, "Auto", marca, modelo, anioFabricacion,
                color, esUsado, tuvoMantenimiento, null, carroceria, cantidadDePuertas, tipoCombustible, transmision);

        System.out.println(">>> Auto cargado correctamente <<<");

        return nuevoAuto;
    }

    public static Camioneta insertarCamioneta() {
        System.out.println("----- Ingrese los datos de la Camioneta -----");

        System.out.print("Patente: ");
        String patente = entrada.nextLine().toUpperCase();

        System.out.print("Marca: ");
        String marca = entrada.nextLine().toUpperCase();

        System.out.print("Modelo: ");
        String modelo = entrada.nextLine().toUpperCase();

        System.out.print("Año de fabricación: ");
        Integer anioFabricacion = Integer.parseInt(entrada.nextLine());

        System.out.print("Color: ");
        String color = entrada.nextLine().toUpperCase();

        System.out.print("¿Es usado?(Si/No): ");
        boolean esUsado = entrada.nextLine().equalsIgnoreCase("SI");;

        System.out.print("¿Tuvo mantenimiento? (Si/No): ");
        boolean tuvoMantenimiento = entrada.nextLine().equalsIgnoreCase("SI");;

        System.out.print("Tipo de Cabina (ej: Simple, Doble, etc): ");
        String tipoCabina = entrada.nextLine().toUpperCase();

        System.out.print("Capacidad de Carga (Litros): ");
        Integer capacidadDeCarga = Integer.parseInt(entrada.nextLine());

        System.out.print("Tipo de Traccion (ej: 4x2, 4x4): ");
        String tipoTraccion = entrada.nextLine().toUpperCase();

        System.out.print("Tiene Caja Cubierta (Si, No): ");
        Boolean tieneCajaCubierta = entrada.nextLine().equalsIgnoreCase("SI");;

        // Crear la instancia
        Camioneta nuevaCamioneta = new Camioneta (patente, "Auto", marca, modelo, anioFabricacion, color,
                esUsado, tuvoMantenimiento, null, tipoCabina, capacidadDeCarga, tipoTraccion, tieneCajaCubierta);

        System.out.println(">>> Camioneta cargado correctamente <<<");

        return nuevaCamioneta;
    }
}
