package br.com.ocoelhogabriel.link.bio.domain.dto.request;

import java.math.BigInteger;

import br.com.ocoelhogabriel.link.bio.domain.entity.ServicesBio;
import br.com.ocoelhogabriel.link.bio.domain.model.ServiceBioModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ServiceModel", description = "Dados para cadastro de serviço")
public class CreateUpdateServicesBioDTO extends ServiceBioModel {

    public CreateUpdateServicesBioDTO(BigInteger userId, String linkDeBio) {
        super(userId, linkDeBio);
    }

    public ServicesBio toEntity() {
        return new ServicesBio(null, getUserId(), getLinkDeBio());
    }

    public ServicesBio toEntity(BigInteger id) {
        return new ServicesBio(id, getUserId(), getLinkDeBio());
    }
}
