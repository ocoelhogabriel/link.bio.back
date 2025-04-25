package br.com.ocoelhogabriel.link.bio.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatusCode;

public class ResponseGenericApi {

    private LocalDateTime timestamp;
    private HttpStatusCode code;
    private String message;
    private List<String> errors;

    public ResponseGenericApi(LocalDateTime timestamp, HttpStatusCode code, String message, List<String> errors) {
        super();
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public ResponseGenericApi(HttpStatusCode code, String message, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatusCode getCode() {
        return code;
    }

    public void setCode(HttpStatusCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResponseGenericApi [timestamp=");
        builder.append(timestamp);
        builder.append(", code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append(", errors=");
        builder.append(errors);
        builder.append("]");
        return builder.toString();
    }

}
