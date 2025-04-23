package br.com.ocoelhogabriel.link.bio.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginRequestDTO", description = "Requisição de login")
public class LoginRequestDTO {

    @Schema(description = "Login do usuário", example = "gabriel@email.com")
    public String login;

    @Schema(description = "Senha de acesso", example = "123456")
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
