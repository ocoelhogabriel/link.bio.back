package br.com.ocoelhogabriel.domain.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import br.com.ocoelhogabriel.domain.model.UserModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserResponseDTO", description = "Dados retornados de um usuário")
public class UserResponseDTO extends UserModel {

    @Schema(description = "Identificador dos dados dos Links (UUID)", example = "e4b1e302-f9c7-4ad3-bb8b-5a3e9a33fd2d", nullable = false)
    public UUID id;

    public UserResponseDTO(String name, String email, String password, LocalDate birthDate, String phone, UUID id, String name2, String email2, LocalDate birthDate2, String phone2) {
        super(name, email, password, birthDate, phone);
        this.id = id;
        name = name2;
        email = email2;
        birthDate = birthDate2;
        phone = phone2;
    }

}
