package br.com.ocoelhogabriel.link.bio.domain.dto.response;

import java.math.BigInteger;

import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.domain.enuns.UserRole;
import br.com.ocoelhogabriel.link.bio.domain.model.RegisterModel;

public class AccessResponseDTO extends RegisterModel {

    protected AccessResponseDTO(BigInteger userId, String login, String password, UserRole role) {
        super(userId, login, password, role);
        // Auto-generated constructor stub
    }

    public AccessResponseDTO(Access access) {
        super(access.getUserId(), access.getLogin(), null, access.getRole());
    }

}
