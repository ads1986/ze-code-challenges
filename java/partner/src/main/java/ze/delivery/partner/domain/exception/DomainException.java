package ze.delivery.partner.domain.exception;

public class DomainException extends RuntimeException {

    private Error error;

    public DomainException(Error error) {
        String message = String.format(error.getMessage(), error.getField());
        this.error = new Error(message, error.getField());
    }

    public Error getError() {
        return error;
    }

}
