import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaConcesionariaVehiculos implements GestionVehiculos {
    private List<Vehiculo> vehiculos = new ArrayList<>();

    @Override
    public void agregarVehiculo(Vehiculo vehiculo) throws IOException {
        guardarVehiculo(vehiculo);
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public Vehiculo actualizarVehiculo(Vehiculo vehiculoActualizado) {
        return null;
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
        // // Vamos a indicar el nombre del archivo que vamos a crear posteriormente
        // File file = new File("ListadoDeAutosEnMantenimiento.txt");
        // // Creamos el archivo solamente si no existe
        // if (!file.exists()) {
        //     file.createNewFile();
        // }

        // // Se encarga de guardar nuestros vehiculos en el listado, mediante el metodo
        // // ToString para poder
        // // aprovechar la herencia
        // try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
        //     bw.write(vehiculo.toString()); // Utiliza el toString del tipo de vehiculo indicado
        //     bw.newLine();
        //     bw.write("--------------------"); // Vamos a usar este separador para los vehiculos
        //     bw.newLine();
        // }
        this.vehiculos.add(vehiculo);
    }

    @Override
    public void guardarVehiculosFile() throws IOException {
        ObjectOutputStream out = null;
        try {
            File f = new File("vehiculos.dat");
            f.createNewFile();

            out = new ObjectOutputStream(new FileOutputStream(f));
            for (Vehiculo vehiculo : this.vehiculos) {
                out.writeObject(vehiculo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public List<Vehiculo> obtenerVehiculosFile() throws IOException {
        //List<Vehiculo> vehiculos = new ArrayList<>();
        File f = new File("vehiculos.dat");

        // Si el archivo no existe o está vacío, retornar lista vacía
        if (!f.exists() || f.length() == 0) {
            return vehiculos;
        }

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(f));

            // Leer objetos hasta que se lance EOFException
            while (true) {
                try {
                    Vehiculo vehiculo = (Vehiculo) in.readObject();
                    vehiculos.add(vehiculo);
                } catch (java.io.EOFException eof) {
                    // Fin del archivo alcanzado
                    break;
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Clase Vehiculo no encontrada");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return vehiculos;
    }

    @Override
    public void mostrarListadoVehiculos() {
        if(vehiculos.isEmpty()){
            System.out.println("No hay vehículos para mostrar.");
            return;
        }
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mostrarInfo();
            System.out.println("--------------------");
        }
       
    }

    //Menu de Opciones
    private void menuOpciones() {
        System.out.println("----- Menú de Opciones -----");
        System.out.println("1. Agregar Vehículo");
        System.out.println("2. Eliminar Vehículo");
        System.out.println("3. Actualizar Vehículo");
        System.out.println("4. Listar Vehículos en Mantenimiento");
        System.out.println("5. Listar Vehículos en Lavadero");
        //System.out.println("6. Guardar Vehículos en Archivo");
        //System.out.println("7. Obtener Vehículos desde Archivo");
        System.out.println("8. Mostrar Listado de Vehículos");
        System.out.println("9. Salir");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione una opción: ");
        do{
            int opcion = scanner.nextInt();
        }while(!scanner.hasNextInt() && );
        

        // Aquí se implementaría la lógica para manejar cada opción seleccionada
        scanner.close();

    }

    public void iniciarSistema() {
        // Aquí se implementaría la lógica para interactuar con el usuario,
        // mostrar el menú y manejar las opciones seleccionadas.
        menuOpciones();
    }
}
