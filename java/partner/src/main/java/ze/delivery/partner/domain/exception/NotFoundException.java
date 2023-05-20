package ze.delivery.partner.domain.exception;

public class NotFoundException extends DomainException {

    public static final String NOT_FOUND_ENTITY = "%s not found";

    public NotFoundException(String message, String field) {
        super(new Error(message, field));
    }
}
