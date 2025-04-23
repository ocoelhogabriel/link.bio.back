package br.com.ocoelhogabriel.link.bio.domain.model;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public abstract class RegisterModel {

  @Schema(description = "ID do usuário")
  @NotNull(message = "ID do usuário não pode ser nulo")
  protected UUID userId;
  @Schema(description = "Login do usuário")
  @NotNull(message = "Login do usuário não pode ser nulo")
  protected String login;
  @Schema(description = "Senha do usuário")
  @NotNull(message = "Senha do usuário não pode ser nulo")
  protected String password;

  protected RegisterModel(UUID idUser, String login, String password) {
    this.userId = idUser;
    this.login = login;
    this.password = password;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID idUser) {
    this.userId = idUser;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "RegisterModel [idUser=" + userId + ", login=" + login + ", password=" + password + "]";
  }
}
