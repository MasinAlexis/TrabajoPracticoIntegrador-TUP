package service.interfaces;

import domain.Vehiculo;

import java.io.IOException;
import java.util.List;

public interface GestionVehiculos {
    void agregarVehiculo() throws IOException;
    void eliminarVehiculoPorPatente() throws IOException;
    void actualizarVehiculo() throws IOException;
    List<Vehiculo> obtenerVehiculosFile() throws IOException;
    void guardarVehiculosFile() throws IOException;
    void obtenerVehiculosTallerFile() throws IOException;
    void obtenerVehiculosLavaderoFile() throws IOException;
    // Guardar colas de vehiculos en taller y lavadero
    void guardarVehiculosTallerFile() throws IOException;
    void guardarVehiculosLavaderoFile() throws IOException;
    void mostrarListadoVehiculos() throws IOException;
    Integer obtenerNumeroCeroKilometro() throws IOException;
    void vehiculosDadosDeBaja() throws IOException;
    void listarVehiculosEnMantenimiento();
    void listarVehiculosEnLavadero();
}