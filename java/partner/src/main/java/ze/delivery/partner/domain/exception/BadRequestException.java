package ze.delivery.partner.domain.exception;

public class BadRequestException extends DomainException {

    public static final String MANDATORY_FIELD = "Field %s is mandatory";

    public BadRequestException(String message, String field) {
        super(new Error(message, field));
    }
}
