package br.com.ocoelhogabriel.link.bio.domain.dto.request;

import java.util.UUID;

import br.com.ocoelhogabriel.link.bio.domain.model.RegisterModel;

public class RegisterRequest extends RegisterModel  {

  protected RegisterRequest(UUID userId, String login, String password) {
    super(userId, login, password);
    //Auto-generated constructor stub
  }
    
  
}
