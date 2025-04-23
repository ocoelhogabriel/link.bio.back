package br.com.ocoelhogabriel.link.bio.domain.dto.response;

import java.util.UUID;

public class AuthResponse {
  private boolean success;
  private String message;
  private String token;
  private UUID userId;

  public AuthResponse(boolean success, String message, String token, UUID userId) {
    this.success = success;
    this.message = message;
    this.token = token;
    this.userId = userId;
  }

  public AuthResponse(String token, UUID userId) {
    this.success = true;
    this.message = "Login successful";
    this.token = token;
    this.userId = userId;
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

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
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
    sb.append(", token=").append(token);
    sb.append(", userId=").append(userId);
    sb.append('}');
    return sb.toString();
  }

}
