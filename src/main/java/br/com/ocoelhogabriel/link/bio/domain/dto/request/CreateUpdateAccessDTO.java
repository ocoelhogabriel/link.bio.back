package br.com.ocoelhogabriel.link.bio.domain.dto.request;

import java.util.UUID;

import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.domain.enuns.UserRole;
import br.com.ocoelhogabriel.link.bio.domain.model.RegisterModel;

public class CreateUpdateAccessDTO extends RegisterModel {

    protected CreateUpdateAccessDTO(UUID userId, String login, String password, UserRole role) {
        super(userId, login, password, role);
        // Auto-generated constructor stub
    }

    public Access toEntity(String password) {
        return new Access(null, this.getUserId(), this.getLogin(), password, null, this.getRole());
    }

    public Access toEntity(UUID id, String password) {
        return new Access(id, this.getUserId(), this.getLogin(), password, null, this.getRole());
    }

    public Access toEntity(UUID id, String token, String password) {
        return new Access(id, this.getUserId(), this.getLogin(), password, token, this.getRole());
    }
}
