package br.com.ocoelhogabriel.link.bio.domain.model;

import java.util.UUID;

public abstract class AuthResponseModel {
    protected boolean success;
    protected String message;
    protected UUID userId;

    protected AuthResponseModel(boolean success, String message, UUID userId) {
        super();
        this.success = success;
        this.message = message;
        this.userId = userId;

    }

    protected AuthResponseModel(UUID userId) {
        this.success = true;
        this.message = "Login successful";
        this.userId = userId;
    }

    protected AuthResponseModel() {
        super();
        // Auto-generated constructor stub
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AuthResponse{");
        sb.append("success=").append(success);
        sb.append(", message=").append(message);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }

}
