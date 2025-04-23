package br.com.ocoelhogabriel.link.bio.domain.model;

import org.springframework.http.HttpStatusCode;

public class ResponseGenericApi {

    private HttpStatusCode code;
    private String message;
    private Throwable error;

    public ResponseGenericApi(HttpStatusCode code, String message, Throwable error) {
        super();
        this.code = code;
        this.message = message;
        this.error = error;
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

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResponseGenericApi [code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append(", data=");
        builder.append(error);
        builder.append("]");
        return builder.toString();
    }

}
