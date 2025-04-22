package br.com.ocoelhogabriel.domain.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public abstract class UserModel {

    @Schema(description = "Nome completo do usuário", example = "Gabriel Coelho")
    protected String name;

    @Schema(description = "E-mail do usuário", example = "gabriel@email.com")
    protected String email;

    @Schema(description = "Senha do usuário", example = "123456")
    protected String password;

    @Schema(description = "Data de nascimento", example = "1990-01-01")
    protected LocalDate birthDate;

    @Schema(description = "Telefone para contato", example = "(11) 99999-0000")
    protected String phone;

    protected UserModel(String name, String email, String password, LocalDate birthDate, String phone) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserModel [");
        if (name != null) {
            builder.append("name=").append(name).append(", ");
        }
        if (email != null) {
            builder.append("email=").append(email).append(", ");
        }
        if (password != null) {
            builder.append("password=").append(password).append(", ");
        }
        if (birthDate != null) {
            builder.append("birthDate=").append(birthDate).append(", ");
        }
        if (phone != null) {
            builder.append("phone=").append(phone);
        }
        builder.append("]");
        return builder.toString();
    }

}
