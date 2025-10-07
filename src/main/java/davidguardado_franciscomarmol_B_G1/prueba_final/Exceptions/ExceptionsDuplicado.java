package davidguardado_franciscomarmol_B_G1.prueba_final.Exceptions;

public class ExceptionsDuplicado extends RuntimeException {
    public ExceptionsDuplicado(String message) {
        super(message);
    }

    public String getColumnDuplicate() {

        return getMessage();
    }
}
