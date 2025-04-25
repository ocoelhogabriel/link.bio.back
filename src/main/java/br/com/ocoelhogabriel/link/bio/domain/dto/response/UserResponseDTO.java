package br.com.ocoelhogabriel.link.bio.domain.dto.response;

import java.math.BigInteger;
import java.time.LocalDate;

import br.com.ocoelhogabriel.link.bio.domain.entity.User;
import br.com.ocoelhogabriel.link.bio.domain.model.UserModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserResponseDTO", description = "Dados retornados de um usuário")
public class UserResponseDTO extends UserModel {

    @Schema(description = "Identificador dos dados dos Links (UUID)", example = "e4b1e302-f9c7-4ad3-bb8b-5a3e9a33fd2d", nullable = false)
    public BigInteger id;

    public UserResponseDTO(String name, String email, LocalDate birthDate, String phone, BigInteger id) {
        super(name, email, birthDate, phone);
        this.id = id;
    }

    public UserResponseDTO(User user) {
        super(user.getName(), user.getEmail(), user.getBirthDate(), user.getPhone());
        this.id = user.getId();
    }

}
