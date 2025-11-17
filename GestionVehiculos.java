import java.io.IOException;
import java.util.List;

public interface GestionVehiculos {
    void agregarVehiculo(Vehiculo vehiculo) throws IOException;
    void eliminarVehiculo(Vehiculo vehiculo);
    Vehiculo actualizarVehiculo(Vehiculo vehiculoActualizado);
    List<Vehiculo> agregarVehiculoAMantenimiento();
    List<Vehiculo> agregarVehiculoALavadero();
    //Gestion de vehiculos en el archivo
    List<Vehiculo> obtenerVehiculosFile() throws IOException;
    void guardarVehiculosFile() throws IOException;
    //Mostrar listado de vehiculos desde el archivo
    void mostrarListadoVehiculos();
}