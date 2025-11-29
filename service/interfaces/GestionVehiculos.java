package service.interfaces;

import domain.Vehiculo;

import java.io.IOException;
import java.util.List;

public interface GestionVehiculos {
    void agregarVehiculo() throws IOException;
    void eliminarVehiculoPorPatente() throws IOException;
    void actualizarVehiculo() throws IOException;
    List<Vehiculo> agregarVehiculoAMantenimiento();
    List<Vehiculo> agregarVehiculoALavadero();
    //Gestion de vehiculos en el archivo
    List<Vehiculo> obtenerVehiculosFile() throws IOException;
    void guardarVehiculosFile() throws IOException;
    //Mostrar listado de vehiculos desde el archivo
    //void mostrarListadoVehiculos();

    //Cargar colas de vehiculos en taller y lavadero
    void obtenerVehiculosTallerFile() throws IOException;
    void obtenerVehiculosLavaderoFile() throws IOException;
    //Guardar colas de vehiculos en taller y lavadero
    void guardarVehiculosTallerFile() throws IOException;
    void guardarVehiculosLavaderoFile() throws IOException;
    void mostrarListadoVehiculos() throws IOException;
}