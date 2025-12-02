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
    private static final String nombreArchivoColaMantenimiento = "vehiculosEnMantenimiento.dat";
    private static final String nombreArchivoColaLavadero = "vehiculosEnLavadero.dat";

    // Listas de Vehiculos
    private List<Vehiculo> vehiculos = new ArrayList<>();
    private GestionCola<Vehiculo> vehiculosEnMantenimiento = new GestionCola<>("Mantenimiento");
    private GestionCola<Vehiculo> vehiculosEnLavadero = new GestionCola<>("Lavadero");

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
        // El try captura las excepciones de los metodos de insercion, no es necesario
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
            System.out.println("El valor ingresado no es válido. Mensaje: " + formatoException.getMessage()
                    + " - Intentelo de nuevo");
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }

    private void guardarVehiculo(Vehiculo vehiculo) {
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
                System.out.println("| 7. Adm. Vehiculo en Taller y Lavadero|");
                System.out.println("| 8. Salir                             |");
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
                        actualizarVehiculo();
                        break;
                    case 4:
                        listarVehiculosEnMantenimiento();
                        pausar();
                        break;
                    case 5:
                        listarVehiculosEnLavadero();
                        pausar();
                        break;
                    case 6:
                        mostrarListadoVehiculos();
                        pausar();
                        break;
                    case 7:
                        // menu de opciones de taller y lavadero
                        menuAdministracionTallerLavadero();
                        break;
                    default:
                        // código si no coincide ningún caso
                        break;
                }

            } while (opcion != 8);
        } catch (NumberFormatException e) {
            System.out.println("\n¡Gracias por usar el Sistema de Concesionaria!");
        } finally {
            // Esto se ejecutará siempre, haya o no excepción
            System.out.println("\n¡Gracias por usar el Sistema de Concesionaria!");
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

    @Override
    public void obtenerVehiculosTallerFile() throws IOException {
        this.vehiculosEnMantenimiento.obtenerColaFile(nombreArchivoColaMantenimiento);
    }

    @Override
    public void obtenerVehiculosLavaderoFile() throws IOException {
        this.vehiculosEnLavadero.obtenerColaFile("vehiculosEnLavadero.dat");
    }

    @Override
    public void guardarVehiculosTallerFile() throws IOException {
        this.vehiculosEnMantenimiento.guardarColaFile(nombreArchivoColaMantenimiento);
    }

    @Override
    public void guardarVehiculosLavaderoFile() throws IOException {
        this.vehiculosEnLavadero.guardarColaFile("vehiculosEnLavadero.dat");
    }

    @Override
    public void listarVehiculosEnMantenimiento() {
        this.vehiculosEnMantenimiento.mostrarCola();
    }

    @Override
    public void listarVehiculosEnLavadero() {
        this.vehiculosEnLavadero.mostrarCola();
    }

    // Privado
    private void menuAdministracionTallerLavadero() {
        try {
            do {
                System.out.println("----------- Menú de Opciones -----------");
                System.out.println("| 1. Enviar Vehículo al Taller         |");
                System.out.println("| 2. Enviar Vehiculo al Lavedro        |");
                System.out.println("| 3. Sacar Vehiculo del Taller         |");
                System.out.println("| 4. Sacar Vehiculo del Lavadero       |");
                System.out.println("| 5. Salir                             |");
                System.out.println("----------------------------------------");

                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());

                // Llamamos a la funcion necesaria para realizar la accion seleccionada
                switch (opcion) {
                    case 1:
                        // agregarVehiculo();
                        enviarVehiculoAlTaller();
                        break;
                    case 2:
                        enviarVehiculoAlLavadero();
                        break;
                    case 3:
                        // código a ejecutar
                        sacarVehiculoDelTaller();
                        break;
                    case 4:
                        // código a ejecutar
                        sacarVehiculoDelLavadero();
                        break;
                    case 5:
                        break;
                    default:
                        // código si no coincide ningún caso
                        break;
                }

            } while (opcion != 5);
        } catch (NumberFormatException e) {
            System.out.println("\n¡Gracias por usar el Sistema de Concesionaria!");
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        } finally {
            // Esto se ejecutará siempre, haya o no excepción
        }

    }

    private void enviarVehiculoAlTaller() throws IOException {
        System.out.println("------------ Enviar Vehículo al Taller ------------");
        System.out.print("Ingrese la patente del vehículo a enviar al taller: ");
        String patente = scanner.nextLine().toUpperCase();

        // Buscar el vehículo en la lista de vehículos
        Vehiculo vehiculoEncontrado = null;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPatente().equalsIgnoreCase(patente)) {
                vehiculoEncontrado = vehiculo;
                break;
            }
        }

        if (this.vehiculosEnMantenimiento.obtenerCola().contains(vehiculoEncontrado)) {
            System.out.println("El vehículo con patente " + patente + " ya se encuentra en el taller.");
            pausar();
            return;
        }

        if (vehiculoEncontrado != null) {
            // Agregar el vehículo a la cola de mantenimiento
            this.vehiculosEnMantenimiento.agregar(vehiculoEncontrado);
            System.out.println("Vehículo con patente " + patente + " enviado al taller.");
            // Guardar la cola actualizada en el archivo
            guardarVehiculosTallerFile();
        } else {
            System.out.println("Vehículo con patente " + patente + " no encontrado.");
        }
        pausar();
    }

    private void enviarVehiculoAlLavadero() throws IOException {
        System.out.println("------------ Enviar Vehículo al Lavadero ------------");
        System.out.print("Ingrese la patente del vehículo a enviar al taller: ");
        String patente = scanner.nextLine().toUpperCase();

        // Buscar el vehículo en la lista de vehículos
        Vehiculo vehiculoEncontrado = null;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPatente().equalsIgnoreCase(patente)) {
                vehiculoEncontrado = vehiculo;
                break;
            }
        }

        if (this.vehiculosEnLavadero.obtenerCola().contains(vehiculoEncontrado)) {
            System.out.println("El vehículo con patente " + patente + " ya se encuentra en el Lavadero.");
            pausar();
            return;
        }

        if (vehiculoEncontrado != null) {
            // Agregar el vehículo a la cola de mantenimiento
            this.vehiculosEnLavadero.agregar(vehiculoEncontrado);
            System.out.println("Vehículo con patente " + patente + " enviado al taller.");
            // Guardar la cola actualizada en el archivo
            guardarVehiculosTallerFile();
        } else {
            System.out.println("Vehículo con patente " + patente + " no encontrado.");
        }
        pausar();
    }

    private void sacarVehiculoDelTaller() throws IOException {
        System.out.println("------------ Sacando Vehiculo del Taller y Enviarlo al Lavadero ------------");

        Vehiculo vehiculoProcesado = this.vehiculosEnMantenimiento.procesar();
        if (vehiculoProcesado != null) {
            System.out
                    .println("Vehículo con patente " + vehiculoProcesado.getPatente() + " ha sido sacado del taller.");
            // Actualizar el atributo tuvoMantenimiento en la lista principal
            vehiculos.stream()
                    .filter(v -> v.getPatente().equals(vehiculoProcesado.getPatente()))
                    .findFirst()
                    .ifPresent(v -> {
                        v.setTuvoMantenimiento(true);
                        System.out.println(
                                "Vehículo con patente " + vehiculoProcesado.getPatente() + " marcado como mantenido.");
                    });
            this.vehiculosEnLavadero.agregar(vehiculoProcesado);
            System.out.println("Vehículo con patente " + vehiculoProcesado.getPatente() + " se envio al Lavadero.");

            this.guardarVehiculosFile();
            this.vehiculosEnMantenimiento.guardarColaFile(nombreArchivoColaMantenimiento);
            this.vehiculosEnLavadero.guardarColaFile(nombreArchivoColaLavadero);
            pausar();
        }
    }

    private void sacarVehiculoDelLavadero() throws IOException {
        System.out.println("------------ Sacando Vehiculo del Lavadero ------------");

        Vehiculo vehiculoProcesado = this.vehiculosEnLavadero.procesar();
        if (vehiculoProcesado != null) {
            System.out
                    .println(
                            "Vehículo con patente " + vehiculoProcesado.getPatente() + " ha sido sacado del lavadero.");

            this.vehiculosEnLavadero.guardarColaFile(nombreArchivoColaLavadero);
            pausar();
        }
    }

    private void pausar() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
