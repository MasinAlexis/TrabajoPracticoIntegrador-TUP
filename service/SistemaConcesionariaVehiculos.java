package service;

import domain.Vehiculo;
import excepciones.ExceptionVechiculoNoEncontrado;
import service.interfaces.GestionVehiculos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SistemaConcesionariaVehiculos implements GestionVehiculos {
    // Listas de Vehiculos
    private List<Vehiculo> vehiculos = new ArrayList<>();
    private Queue<Vehiculo> vehiculosEnMantenimiento = new LinkedList<>();
    private Queue<Vehiculo> vehiculosEnLavadero = new LinkedList<>();

    Scanner scanner = new Scanner(System.in);
    Integer opcion = 0;
    String entrada = "";

    @Override
    public void agregarVehiculo() throws IOException {
        System.out.println("------------ Menú Inserción de Vehiculo ------------");
        System.out.println("| - Seleccione que tipo de vehículo desea agregar  |");
        System.out.println("| 1. Automovil                                     |");
        System.out.println("| 2. Camioneta                                     |");
        System.out.println("| 3. Motocicleta                                   |");
        System.out.println("----------------------------------------------------");
        // El try captura las excepciones de los metodos de insercion, no es necesario
        // un try/catch en los mismos
        try {
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
            System.out.println("El valor ingresado no es válido. Mensaje: " + formatoException.getMessage()
                    + " - Intentelo de nuevo");
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }

    @Override
    public void eliminarVehiculoPorPatente() throws IOException {

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
            System.out.println("El valor ingresado no es válido. Mensaje: " + formatoException.getMessage()
                    + " - Intentelo de nuevo");
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
        } catch (IOException e) {
            System.out.println("Error de IO al guardar los vehículos en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al guardar los vehículos en el archivo: " + e.getMessage());
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

    // Menu de Opciones
    private void menuOpciones() throws IOException {
        try {
            do {
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

                // Llamamos a la funcion necesaria para realizar la accion seleccionada
                switch (opcion) {
                    case 1:
                        agregarVehiculo();
                        break;
                    case 2:
                        eliminarVehiculoPorPatente();
                        break;
                    case 3:
                        // código a ejecutar
                        break;
                    case 4:
                        // código a ejecutar
                        listarVehiculosEnMantenimiento();
                        break;
                    case 5:
                        // código a ejecutar
                        listarVehiculosEnLavadero();
                        break;
                    case 6:
                        // código a ejecutar
                        break;
                    default:
                        // código si no coincide ningún caso
                        break;
                }

            } while (opcion != 7);
        } catch (NumberFormatException e) {
            System.out.println("\n¡Gracias por usar el Sistema de Concesionaria!");
        } finally {

            // Aquí se implementaría la lógica para manejar cada opción seleccionada
            scanner.close();
        }
    }

    public void iniciarSistema() throws IOException {
        // Aquí se implementaría la lógica para interactuar con el usuario,
        // mostrar el menú y manejar las opciones seleccionadas.
        this.obtenerVehiculosFile();
        this.obtenerVehiculosTallerFile();
        this.obtenerVehiculosLavaderoFile();
        menuOpciones();
    }

    @Override
    public void obtenerVehiculosTallerFile() throws IOException {
        this.vehiculosEnMantenimiento = this.obtenerColaFile("vehiculos_en_mantenimiento.dat");
    }

    @Override
    public void obtenerVehiculosLavaderoFile() throws IOException {
        this.vehiculosEnLavadero = this.obtenerColaFile("vehiculos_en_lavadero.dat");
    }

    @Override
    public void guardarVehiculosTallerFile() throws IOException {
        this.guardarColaFile("vehiculos_en_mantenimiento.dat", this.vehiculosEnMantenimiento);
    }

    @Override
    public void guardarVehiculosLavaderoFile() throws IOException {
        this.guardarColaFile("vehiculos_en_lavadero.dat", this.vehiculosEnLavadero);
    }

    @Override
    public void listarVehiculosEnMantenimiento() {
        this.mostrarVehiculosEnCola(this.vehiculosEnMantenimiento);
    }

    @Override
    public void listarVehiculosEnLavadero() {
        this.mostrarVehiculosEnCola(this.vehiculosEnLavadero);
    }

    // Privado
    private boolean guardarColaFile(String nombreArchivo, Queue<Vehiculo> cola) throws IOException {
        // Implementación para guardar la cola en un archivo
        FileOutputStream fileOutput = new FileOutputStream(nombreArchivo, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOutput);
        try (out) {
            for (Vehiculo elemento : cola) {
                out.writeObject(elemento);
            }
        } catch (IOException e) {
            System.out.println("Error de IO al guardar la cola en el archivo: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al guardar la cola en el archivo: " + e.getMessage());
            return false;
        }
        return true;
    }

    private Queue<Vehiculo> obtenerColaFile(String nombreArchivo) throws IOException {
        Queue<Vehiculo> cola = new LinkedList<Vehiculo>();
        File archivo = new File(nombreArchivo);

        if (!archivo.exists() || archivo.length() == 0) {
            return cola;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            while (true) {
                try {
                    // //Suprime el warnign de tipo de clase al desiarrializar
                    // @SuppressWarnings("unchecked")
                    Vehiculo v = (Vehiculo) in.readObject();
                    cola.add(v);
                } catch (EOFException eof) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error de IO al leer la cola desde el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error al leer la cola desde el archivo: " + e.getMessage());
        }
        return cola;
    }

    private void mostrarVehiculosEnCola(Queue<Vehiculo> cola) {
        System.out.println("----- Listado de Vehículos en "
                + (cola == vehiculosEnMantenimiento ? "Mantenimiento" : "Lavadero") + " -----");
        if (cola.isEmpty()) {
            System.out.println("No hay vehículos para mostrar.");
            return;
        }
        for (Vehiculo vehiculo : cola) {
            vehiculo.mostrarInfo();
            System.out.println("--------------------");
        }
    }
}
