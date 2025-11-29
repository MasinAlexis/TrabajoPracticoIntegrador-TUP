package excepciones;

public class ExceptionVechiculoNoEncontrado extends RuntimeException {

    public ExceptionVechiculoNoEncontrado(String motivoExcepcion) {
        super(motivoExcepcion);
    }
}
