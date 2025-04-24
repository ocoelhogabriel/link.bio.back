package br.com.ocoelhogabriel.link.bio.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "LoginRequestDTO", description = "Requisição de login")
public class LoginRequestDTO {

    @NotNull(message = "Login não pode ser nulo")
    @Schema(description = "Login do usuário", defaultValue = "admin", example = "admin ou admin@admin.com", nullable = false)
    public String login;

    @NotNull(message = "Senha não pode ser nula")
    @Schema(description = "Senha de acesso", defaultValue = "admin", example = "admin", nullable = false)
    public String password;

    public LoginRequestDTO() {
        super();
        // Auto-generated constructor stub
    }

    public LoginRequestDTO(String login, String password) {
        super();
        this.login = login;
        this.password = password;
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
        StringBuilder builder = new StringBuilder();
        builder.append("LoginRequestDTO [");
        if (login != null) {
            builder.append("login=").append(login).append(", ");
        }
        if (password != null) {
            builder.append("password=").append(password);
        }
        builder.append("]");
        return builder.toString();
    }

}
