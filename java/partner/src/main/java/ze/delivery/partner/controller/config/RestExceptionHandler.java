package ze.delivery.partner.controller.config;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import ze.delivery.partner.controller.mapper.ControllerMapper;
import ze.delivery.partner.domain.exception.BadRequestException;
import ze.delivery.partner.domain.exception.DomainException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    private static final String REQUEST_VALIDATION_ERRORS
            = "Request validation errors";
    private final ControllerMapper mapper = Mappers.getMapper(ControllerMapper.class);

    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            DomainException ex, WebRequest request) {

        ResponseError responseError = mapper.toResponseError(ex.getError());

        return new ResponseEntity<>(
                responseError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>>
    handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> details = new ArrayList<String>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
