package br.com.ocoelhogabriel.link.bio.domain.dto.request;

import java.time.LocalDate;

import br.com.ocoelhogabriel.link.bio.domain.model.UserModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserModel", description = "Dados para criação de usuário")
public class CreateUpdateUserDTO extends UserModel {

    protected CreateUpdateUserDTO(String name, String email, String password, LocalDate birthDate, String phone) {
        super(name, email, password, birthDate, phone);
    }

}
