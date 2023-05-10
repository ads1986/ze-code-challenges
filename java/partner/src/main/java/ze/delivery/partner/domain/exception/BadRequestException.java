package ze.delivery.partner.domain.exception;

public class BadRequestException extends DomainException {
    public BadRequestException(String message, String field) {
        super(new Error(message, field));
    }
}
