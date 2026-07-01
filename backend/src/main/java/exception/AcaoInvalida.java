package exception;

public class AcaoInvalida extends RuntimeException {
    public AcaoInvalida(String message) {
        super(message);
    }
}
