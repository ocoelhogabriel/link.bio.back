package br.com.ocoelhogabriel.link.bio.domain.dto.request;

import java.util.UUID;

import br.com.ocoelhogabriel.link.bio.domain.model.ServiceModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ServiceModel", description = "Dados para cadastro de serviço")
public class CreateUpdateServiceDTO extends ServiceModel {

    public CreateUpdateServiceDTO(UUID userId, String linkDeBio) {
        super(userId, linkDeBio);
    }

}
