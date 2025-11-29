package service;

import domain.Auto;
import domain.Motocicleta;
import domain.Camioneta;

import java.util.Scanner;

public class InsercionVehiculos {

    private static Scanner entrada = new Scanner(System.in);

    public static Motocicleta insertarMotocicleta() {

        System.out.println("----- Ingrese los datos de la Moto -----");

        System.out.print("Patente: ");
        String patente = entrada.nextLine();

        System.out.print("Marca: ");
        String marca = entrada.nextLine();

        System.out.print("Modelo: ");
        String modelo = entrada.nextLine();

        System.out.print("Año de fabricación: ");
        int anioFabricacion = Integer.parseInt(entrada.nextLine());

        System.out.print("Color: ");
        String color = entrada.nextLine();

        System.out.print("¿Es usado?(Si/No): ");
        boolean esUsado = entrada.nextLine().equals("Si");

        System.out.print("¿Tuvo mantenimiento? (Si/No): ");
        boolean tuvoMantenimiento = entrada.nextLine().equals("Si");

        System.out.print("Tipo de moto (ej: Enduro, Naked, etc): ");
        String tipoMoto = entrada.nextLine();

        System.out.print("Cilindrada: ");
        Integer cilindrada = Integer.parseInt(entrada.nextLine());

        System.out.print("Tipo de motor (ej: 4T, 2T): ");
        String tipoMotor = entrada.nextLine();

        System.out.print("¿Tiene baúl? (Si/No): ");
        boolean tieneBaul = entrada.nextLine().equals("Si");

        // Crear la instancia
        Motocicleta moto = new Motocicleta(patente, "Motocicleta", marca, modelo, anioFabricacion,
                color, esUsado, tuvoMantenimiento, tipoMoto, cilindrada, tipoMotor, tieneBaul);

        System.out.println(">>> Motocicleta cargada correctamente.");

        return moto;
    }

    public static Auto insertarAutomovil() {
        System.out.println("----- Ingrese los datos del Auto -----");

        System.out.print("Patente: ");
        String patente = entrada.nextLine();

        System.out.print("Marca: ");
        String marca = entrada.nextLine();

        System.out.print("Modelo: ");
        String modelo = entrada.nextLine();

        System.out.print("Año de fabricación: ");
        int anioFabricacion = Integer.parseInt(entrada.nextLine());

        System.out.print("Color: ");
        String color = entrada.nextLine();

        System.out.print("¿Es usado?(Si/No): ");
        boolean esUsado = entrada.nextLine().equals("Si");

        System.out.print("¿Tuvo mantenimiento? (Si/No): ");
        boolean tuvoMantenimiento = entrada.nextLine().equals("Si");

        System.out.print("Tipo de Carroceria (ej: Sedan, Coupé, Familiar, etc): ");
        String carroceria = entrada.nextLine();

        System.out.print("Cantidad de Puertas: ");
        Integer cantidadDePuertas = Integer.parseInt(entrada.nextLine());

        System.out.print("Tipo de combustible (ej: Nafta, Gasoil, etc): ");
        String tipoCombustible = entrada.nextLine();

        System.out.print("Tipo Transmision (Manual, Automatica): ");
        String transmision = entrada.nextLine();

        // Crear la instancia
        Auto nuevoAuto = new Auto(patente, "Auto", marca, modelo, anioFabricacion,
                color, esUsado, tuvoMantenimiento, carroceria, cantidadDePuertas, tipoCombustible, transmision);

        System.out.println(">>> Auto cargado correctamente.");

        return nuevoAuto;
    }

    public static Camioneta insertarCamioneta() {
        System.out.println("----- Ingrese los datos de la Camioneta -----");

        System.out.print("Patente: ");
        String patente = entrada.nextLine();

        System.out.print("Marca: ");
        String marca = entrada.nextLine();

        System.out.print("Modelo: ");
        String modelo = entrada.nextLine();

        System.out.print("Año de fabricación: ");
        int anioFabricacion = Integer.parseInt(entrada.nextLine());

        System.out.print("Color: ");
        String color = entrada.nextLine();

        System.out.print("¿Es usado?(Si/No): ");
        boolean esUsado = entrada.nextLine().equals("Si");

        System.out.print("¿Tuvo mantenimiento? (Si/No): ");
        boolean tuvoMantenimiento = entrada.nextLine().equals("Si");

        System.out.print("Tipo de Cabina (ej: Simple, Doble, etc): ");
        String tipoCabina = entrada.nextLine();

        System.out.print("Capacidad de Carga (Litros): ");
        Integer capacidadDeCarga = Integer.parseInt(entrada.nextLine());

        System.out.print("Tipo de Traccion (ej: 4x2, 4x4): ");
        String tipoTraccion = entrada.nextLine();

        System.out.print("Tiene Caja Cubierta (Si, No): ");
        Boolean tieneCajaCubierta = entrada.nextLine().equals("Si");

        // Crear la instancia
        Camioneta nuevaCamioneta = new Camioneta (patente, "Auto", marca, modelo, anioFabricacion, color,
                esUsado, tuvoMantenimiento, tipoCabina, capacidadDeCarga, tipoTraccion, tieneCajaCubierta);

        System.out.println(">>> Camioneta cargado correctamente.");

        return nuevaCamioneta;
    }
}
