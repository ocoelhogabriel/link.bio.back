package br.com.ocoelhogabriel.link.bio.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.ocoelhogabriel.link.bio.domain.model.ResponseGenericApi;

public class ResponseUtil {

    protected ResponseUtil() {
        super();
    }

    public static ResponseEntity<Object> notFound(String message, Throwable data) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseGenericApi(HttpStatus.NOT_FOUND, message, data));
    }

    public static ResponseEntity<Object> badRequest(String message, Throwable data) {
        return ResponseEntity.badRequest().body(new ResponseGenericApi(HttpStatus.BAD_REQUEST, message, data));
    }

    public static ResponseEntity<Object> serverError(String message, Throwable data) {
        return ResponseEntity.internalServerError().body(new ResponseGenericApi(HttpStatus.INTERNAL_SERVER_ERROR, message, data));
    }

    public static ResponseEntity<Object> accepted(String message) {
        return ResponseEntity.accepted().body(new ResponseGenericApi(HttpStatus.ACCEPTED, message, null));
    }

    public static ResponseEntity<Object> unauthorized(String message, Throwable data) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseGenericApi(HttpStatus.UNAUTHORIZED, message, data));
    }
}
