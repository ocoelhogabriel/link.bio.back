package br.com.ocoelhogabriel.link.bio.domain.dto.response;

import java.math.BigInteger;

import br.com.ocoelhogabriel.link.bio.domain.model.AuthResponseModel;

public class AuthResponse extends AuthResponseModel {

    public AuthResponse(boolean success, String message, BigInteger userId) {
        super(success, message, userId);

    }

}
