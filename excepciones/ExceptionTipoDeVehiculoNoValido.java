package excepciones;

public class ExceptionTipoDeVehiculoNoValido extends RuntimeException {

    public ExceptionTipoDeVehiculoNoValido(String motivoExcepcion) {
        super(motivoExcepcion);
    }
}
