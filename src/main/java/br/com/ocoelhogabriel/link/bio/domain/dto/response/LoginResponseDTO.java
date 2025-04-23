package br.com.ocoelhogabriel.link.bio.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginResponseDTO", description = "Resposta de autenticação")
public class LoginResponseDTO {

    public String token;

    public LoginResponseDTO() {
        // Auto-generated constructor stub
    }

}
