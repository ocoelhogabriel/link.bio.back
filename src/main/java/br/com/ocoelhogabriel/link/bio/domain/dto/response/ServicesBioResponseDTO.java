package br.com.ocoelhogabriel.link.bio.domain.dto.response;

import java.math.BigInteger;

import br.com.ocoelhogabriel.link.bio.domain.entity.ServicesBio;
import br.com.ocoelhogabriel.link.bio.domain.model.ServiceBioModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ServiceResponseDTO", description = "Informações de serviço vinculado ao Link de Bio")
public class ServicesBioResponseDTO extends ServiceBioModel {

    @Schema(description = "Identificador dos dados dos Links (UUID)", example = "e4b1e302-f9c7-4ad3-bb8b-5a3e9a33fd2d", nullable = false)
    public BigInteger id;

    public ServicesBioResponseDTO(BigInteger userId, String linkDeBio, BigInteger id) {
        super(userId, linkDeBio);
        this.id = id;
    }

    public ServicesBioResponseDTO(ServicesBio serviceBio) {
        super(serviceBio.getUserId(), serviceBio.getLinkDeBio());
        this.id = serviceBio.getId();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public ServicesBio toEntity() {
        return new ServicesBio(id, getUserId(), getLinkDeBio());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ServiceResponseDTO [");
        if (id != null) {
            builder.append("id=").append(id);
        }
        builder.append("]");
        return builder.toString();
    }

}
