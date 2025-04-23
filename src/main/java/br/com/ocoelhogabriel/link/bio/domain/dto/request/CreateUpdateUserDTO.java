package br.com.ocoelhogabriel.link.bio.domain.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import br.com.ocoelhogabriel.link.bio.domain.entity.User;
import br.com.ocoelhogabriel.link.bio.domain.model.UserModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserModel", description = "Dados para criação de usuário")
public class CreateUpdateUserDTO extends UserModel {

    protected CreateUpdateUserDTO(String name, String email, LocalDate birthDate, String phone) {
        super(name, email, birthDate, phone);
    }

    public User toEntity() {
        return new User(null, getName(), getEmail(), getBirthDate(), getPhone());
    }

    public User toEntity(UUID id) {
        return new User(id, getName(), getEmail(), getBirthDate(), getPhone());
    }
}
