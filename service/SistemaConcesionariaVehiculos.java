package service;

import domain.Vehiculo;
import excepciones.ExceptionVechiculoNoEncontrado;
import service.interfaces.GestionVehiculos;

import java.io.IOException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaConcesionariaVehiculos implements GestionVehiculos {

    private List<Vehiculo> vehiculos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Integer opcion = 0;
    String entrada = "";

    @Override
    public void agregarVehiculo() {
        System.out.println("------------ Menú Inserción de Vehiculo ------------");
        System.out.println("| - Seleccione que tipo de vehículo desea agregar  |");
        System.out.println("| 1. Automovil                                     |");
        System.out.println("| 2. Camioneta                                     |");
        System.out.println("| 3. Motocicleta                                   |");
        System.out.println("----------------------------------------------------");
        // El try captura las excepciones de los metodos de insercion, no es necesario un try/catch en los mismos
        try{
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1:
                    guardarVehiculo(InsertarVehiculos.insertarAutomovil());
                    break;
                case 2:
                    guardarVehiculo(InsertarVehiculos.insertarCamioneta());
                    break;
                case 3:
                    guardarVehiculo(InsertarVehiculos.insertarMotocicleta());
                    break;
                default:
                    throw new IllegalArgumentException("Opción elegida no es válida. Vuelva a intentar.");
            }
            // Guardamos solamente si sale solamente bien
            guardarVehiculosFile();
        } catch (NumberFormatException formatoException) {
            System.out.println("El valor ingresado no es válido. Mensaje: " + formatoException.getMessage() + " - Intentelo de nuevo");
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }

    @Override
    public void eliminarVehiculoPorPatente() {

        System.out.println("------------ Menú de eliminacion de Vehiculo ---------");
        System.out.println("| - Ingrese la patente del vehículo a eliminar       |");
        System.out.println("------------------------------------------------------");
        try {
            System.out.print("Patente: ");
            entrada = scanner.nextLine().toUpperCase();
            // El metodo ya carga nuestro atributo vehiculos con la lista actual
            List<Vehiculo> listadoVehiculos = obtenerVehiculosFile();

            // Asignamos a nuestra lista la actualizada
            this.vehiculos = EliminarVehiculo.bajaLogicaDeVehiculo(listadoVehiculos, entrada);
            guardarVehiculosFile();
            System.out.println("Vehículo dado de baja correctamente.");
        } catch (ExceptionVechiculoNoEncontrado exceptionVechiculoNoEncontrado) {
            System.out.println("Se ha producido un error. " + exceptionVechiculoNoEncontrado.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }

    @Override
    public void actualizarVehiculo() throws IOException {
        // El metodo ya carga nuestro atributo vehiculos con la lista actual
        List<Vehiculo> listadoVehiculos = obtenerVehiculosFile();

        System.out.println("--------------- Menú Actualizar Vehiculo ---------------");
        System.out.println("| - Seleccione que tipo de vehículo desea actualizar   |");
        System.out.println("| 1. Automovil                                         |");
        System.out.println("| 2. Camioneta                                         |");
        System.out.println("| 3. Motocicleta                                       |");
        System.out.println("--------------------------------------------------------");
        try {
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese la patente del vehículo: ");
            entrada = scanner.nextLine().toUpperCase();

            // Asignamos a nuestra lista la actualizada
            this.vehiculos = ActualizarVehiculo.actualizarVehiculo(listadoVehiculos, opcion, entrada);
            guardarVehiculosFile();
            System.out.println("Vehículo actualizado correctamente.");
        } catch (NumberFormatException formatoException) {
            System.out.println("El valor ingresado no es válido. Mensaje: " + formatoException.getMessage() + " - Intentelo de nuevo");
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
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
    public void mostrarListadoVehiculos() throws IOException {
        guardarVehiculosFile();
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos para mostrar.");
            return;
        }
        System.out.println("-- Vehiculos Cargados -----------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mostrarInfo();
            System.out.println("---------------------------------------");
        }
    }

    //Menu de Opciones
    private void menuOpciones() throws IOException {
        do{
            try {
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
                opcion = Integer.parseInt(scanner.nextLine());

                //Llamamos a la funcion necesaria para realizar la accion seleccionada
                switch (opcion) {
                    case 1:
                        agregarVehiculo();
                        break;
                    case 2:
                        eliminarVehiculoPorPatente();
                        break;
                    case 3:
                        actualizarVehiculo();
                        break;
                    case 4:
                        obtenerNumeroCeroKilometro();
                        break;
                    case 5:
                        // código a ejecutar
                        break;
                    case 6:
                        mostrarListadoVehiculos();
                        break;
                    default:
                        // código si no coincide ningún caso
                        break;
                }

            } catch (NumberFormatException formatoException) {
                System.out.println("El valor ingresado no es válido. Mensaje: " + formatoException.getMessage() + " - Intentelo de nuevo");
            } catch (Exception e) {
                System.out.println("Se ha producido un error: " + e.getMessage());
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

    @Override
    public Integer obtenerNumeroCeroKilometro() throws IOException {

        List<Vehiculo> listado = obtenerVehiculosFile();
        Integer identificadorTemporal = 0;
        for (Vehiculo vehiculo : listado) {
            if(!vehiculo.getEsUsado()) {
                identificadorTemporal += 1;
            }
        }
        return identificadorTemporal+1;
    }
}
