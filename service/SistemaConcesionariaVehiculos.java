package service;

import domain.Vehiculo;
import service.interfaces.GestionVehiculos;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaConcesionariaVehiculos implements GestionVehiculos {

    private List<Vehiculo> vehiculos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Integer opcion = 0;

    @Override
    public void agregarVehiculo() throws IOException {
        System.out.println("------------ Menú Inserción de domain.Vehiculo ------------");
        System.out.println("| - Seleccione que tipo de vehículo desea agregar         |");
        System.out.println("| 1. Automovil                                            |");
        System.out.println("| 2. Camioneta                                            |");
        System.out.println("| 3. Motocicleta                                          |");
        System.out.println("-----------------------------------------------------------");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                guardarVehiculo(InsercionVehiculos.insertarAutomovil());
                break;
            case 2:
                guardarVehiculo(InsercionVehiculos.insertarCamioneta());
                break;
            case 3:
                guardarVehiculo(InsercionVehiculos.insertarMotocicleta());
                break;
            default:
                System.out.println("Tratar excepcion");
                break;
        }
        guardarVehiculosFile();
    }

    @Override
    public void eliminarVehiculoByDni(String patente) throws IOException {
        // El metodo ya carga nuestro atributo vehiculos con la lista actual
        List<Vehiculo> listadoVehiculos = obtenerVehiculosFile();

        Vehiculo vehiculo = listadoVehiculos.stream()
                .filter(v -> v.getPatente().equals(patente))
                .findFirst()
                .orElse(null);

        if (vehiculo == null) {
            System.out.println("Agregar excepcion para: No existe un vehiculo con ese titular.");
        }
        // Al modificar el objeto, lo estamos modificando dentro del mismo listado
        vehiculo.setFechaBaja(LocalDate.now());

        // Asignamos a nuestra lista la actualizada
        this.vehiculos = listadoVehiculos;
        guardarVehiculosFile();
        System.out.println("Vehículo dado de baja correctamente.");
    }

    @Override
    public Vehiculo actualizarVehiculo(Vehiculo vehiculoActualizado) throws IOException {
        // El metodo ya carga nuestro atributo vehiculos con la lista actual
        List<Vehiculo> listadoVehiculos = obtenerVehiculosFile();

        Vehiculo vehiculo = listadoVehiculos.stream()
                .filter(v -> v.getPatente().equals(vehiculoActualizado.getPatente()))
                .findFirst()
                .orElse(null);

        if (vehiculo == null) {
            System.out.println("Agregar excepcion para: No existe un vehiculo con ese titular.");
        }

        // Actualizamos nuestro vehiculo
        vehiculo.setPatente(vehiculoActualizado.getPatente());
        vehiculo.setTipo(vehiculoActualizado.getTipo());
        vehiculo.setMarca(vehiculoActualizado.getMarca());
        vehiculo.setModelo(vehiculoActualizado.getModelo());
        vehiculo.setAnioFabricacion(vehiculoActualizado.getAnioFabricacion());
        vehiculo.setColor(vehiculoActualizado.getColor());
        vehiculo.setEsUsado(vehiculoActualizado.getEsUsado());
        vehiculo.setTuvoMantenimiento(vehiculoActualizado.getTuvoMantenimiento());
        vehiculo.setFechaBaja(null);

        // Asignamos a nuestra lista la actualizada
        this.vehiculos = listadoVehiculos;
        guardarVehiculosFile();

        System.out.println("Vehículo actualizado correctamente.");

        return vehiculoActualizado;
    }

    @Override
    public List<Vehiculo> agregarVehiculoAMantenimiento() {
        return List.of();
    }

    @Override
    public List<Vehiculo> agregarVehiculoALavadero() {
        return List.of();
    }

    private void guardarVehiculo(Vehiculo vehiculo) throws IOException {
        this.vehiculos.add(vehiculo);
    }

    @Override
    public List<Vehiculo> obtenerVehiculosFile() throws IOException {
        File archivo = new File("vehiculos.dat");
        vehiculos.clear();

        if (!archivo.exists() || archivo.length() == 0) {
            return vehiculos;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            while (true) {
                try {
                    Vehiculo v = (Vehiculo) in.readObject();
                    vehiculos.add(v);
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    @Override
    public void guardarVehiculosFile() throws IOException {
        // Abrimos nuestro archivo vehiculos.dat y con el parametro false indicamos
        // sobrescribir (no agregar)
        // Si el archivo no existe, lo crea automáticamente sino, si existe, lo borra y
        // empieza desde cero.
        FileOutputStream fileOutput = new FileOutputStream("vehiculos.dat", false);
        ObjectOutputStream out = new ObjectOutputStream(fileOutput);
        try (out) {
            for (Vehiculo v : vehiculos) {
                out.writeObject(v);
            }
        }
    }

    @Override
    public void mostrarListadoVehiculos() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos para mostrar.");
            return;
        }
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mostrarInfo();
            System.out.println("--------------------");
        }

    }

    @Override
    public void vehiculosDadosDeBaja() throws IOException {
        List<Vehiculo> listadoDeBajas = obtenerVehiculosFile()
                .stream()
                .filter(vehiculo -> vehiculo.getFechaBaja() != null)
                .toList();
        for (Vehiculo vehiculo : listadoDeBajas) {
            System.out.println("------------------------------------------------");
            vehiculo.mostrarInfo();
            System.out.println("------------------------------------------------");
        }
    }

    //Menu de Opciones
    private void menuOpciones() throws IOException {

        do{
            System.out.println("----------- Menú de Opciones -----------");
            System.out.println("| 1. Agregar Vehículo                  |");
            System.out.println("| 2. Eliminar Vehículo                 |");
            System.out.println("| 3. Actualizar Vehículo               |");
            System.out.println("| 4. Listar Vehículos en Mantenimiento |");
            System.out.println("| 5. Listar Vehículos en Lavadero      |");
            System.out.println("| 6. Mostrar Listado de Vehículos      |");
            System.out.println("| 7. Salir                             |");
            System.out.println("----------------------------------------");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            //Llamamos a la funcion necesaria para realizar la accion seleccionada
            switch (opcion) {
                case 1:
                    agregarVehiculo();
                    break;
                case 2:
                    // código a ejecutar
                    break;
                case 3:
                    // código a ejecutar
                    break;
                case 4:
                    // código a ejecutar
                    break;
                case 5:
                    // código a ejecutar
                    break;
                case 6:
                    // código a ejecutar
                    break;
                default:
                    // código si no coincide ningún caso
                    break;
            }

        }while(opcion != 7);

        // Aquí se implementaría la lógica para manejar cada opción seleccionada
        scanner.close();

    }

    public void iniciarSistema() throws IOException {
        // Aquí se implementaría la lógica para interactuar con el usuario,
        // mostrar el menú y manejar las opciones seleccionadas.
        menuOpciones();
    }
}
