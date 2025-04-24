package br.com.ocoelhogabriel.link.bio.domain.dto.response;

import java.util.UUID;

import br.com.ocoelhogabriel.link.bio.domain.model.AuthResponseModel;

public class AuthAccessResponse extends AuthResponseModel {
    private String token;
    private String date;
    private String expiryIn;

    public AuthAccessResponse(boolean success, String message, UUID userId, String token, String date, String expiryIn) {
        super(success, message, userId);
        this.token = token;
        this.date = date;
        this.expiryIn = expiryIn;
    }

    public AuthAccessResponse(boolean success, String message, UUID userId) {
        super(success, message, userId);

    }

    public AuthAccessResponse accessGranted(String message, String token, UUID userId, String date, String expiryIn) {
        this.success = true;
        this.message = message;
        this.userId = userId;
        this.token = token;
        this.date = date;
        this.expiryIn = expiryIn;
        return this;
    }

    public AuthAccessResponse() {
        super();
        // Auto-generated constructor stub
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpiryIn() {
        return expiryIn;
    }

    public void setExpiryIn(String expiryIn) {
        this.expiryIn = expiryIn;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthAccessResponse [token=");
        builder.append(token);
        builder.append(", date=");
        builder.append(date);
        builder.append(", expiryIn=");
        builder.append(expiryIn);
        builder.append("]");
        return builder.toString();
    }

}
