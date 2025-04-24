package br.com.ocoelhogabriel.link.bio.domain.dto.response;

import java.util.UUID;

import br.com.ocoelhogabriel.link.bio.domain.model.AuthResponseModel;

public class AuthResponse extends AuthResponseModel {

    public AuthResponse(boolean success, String message, UUID userId) {
        super(success, message, userId);

    }

}
