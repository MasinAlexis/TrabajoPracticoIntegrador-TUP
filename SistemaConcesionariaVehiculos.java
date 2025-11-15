import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SistemaConcesionariaVehiculos implements GestionVehiculos{

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

    private static void guardarVehiculo(Vehiculo vehiculo) throws IOException {
        // Vamos a indicar el nombre del archivo que vamos a crear posteriormente
        File file = new File("ListadoDeAutosEnMantenimiento.txt");
        //Creamos el archivo solamente si no existe
        if (!file.exists()) {
            file.createNewFile();
        }

        //Se encarga de guardar nuestros vehiculos en el listado, mediante el metodo ToString para poder
        //aprovechar la herencia
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(vehiculo.toString()); // Utiliza el toString del tipo de vehiculo indicado
            bw.newLine();
            bw.write("--------------------");  // Vamos a usar este separador para los vehiculos
            bw.newLine();
        }
    }
}
