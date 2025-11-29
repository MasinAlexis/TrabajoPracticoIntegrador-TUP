package service.interfaces;

import domain.Vehiculo;

import java.io.IOException;
import java.util.List;

public interface GestionVehiculos {
    void agregarVehiculo() throws IOException;
    void eliminarVehiculoByDni(Long dniTitular) throws IOException;
    void vehiculosDadosDeBaja() throws IOException;
    Vehiculo actualizarVehiculo(Vehiculo vehiculoActualizado) throws IOException;
    List<Vehiculo> agregarVehiculoAMantenimiento();
    List<Vehiculo> agregarVehiculoALavadero();
    //Gestion de vehiculos en el archivo
    List<Vehiculo> obtenerVehiculosFile() throws IOException;
    void guardarVehiculosFile() throws IOException;
    //Mostrar listado de vehiculos desde el archivo
    void mostrarListadoVehiculos();
}