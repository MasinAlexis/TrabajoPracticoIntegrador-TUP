package excepciones;

public class ExceptionParametrosEntrada extends RuntimeException {

    public ExceptionParametrosEntrada(String motivoExcepcion) {
        super(motivoExcepcion);
    }
}
