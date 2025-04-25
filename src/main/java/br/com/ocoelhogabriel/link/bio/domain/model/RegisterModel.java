package br.com.ocoelhogabriel.link.bio.domain.model;

import java.math.BigInteger;

import br.com.ocoelhogabriel.link.bio.domain.enuns.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public abstract class RegisterModel {

    @Schema(description = "ID do usuário")
    @NotNull(message = "ID do usuário não pode ser nulo")
    protected BigInteger userId;
    @Schema(description = "Login do usuário")
    @NotNull(message = "Login do usuário não pode ser nulo")
    protected String login;
    @Schema(description = "Senha do usuário")
    @NotNull(message = "Senha do usuário não pode ser nulo")
    protected String password;
    @Schema(description = "Senha do usuário")
    @NotNull(message = "Senha do usuário não pode ser nulo")
    protected UserRole role;

    protected RegisterModel(BigInteger idUser, String login, String password, UserRole role) {
        this.userId = idUser;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger idUser) {
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RegisterModel [userId=");
        builder.append(userId);
        builder.append(", login=");
        builder.append(login);
        builder.append(", password=");
        builder.append(password);
        builder.append(", role=");
        builder.append(role);
        builder.append("]");
        return builder.toString();
    }

}
