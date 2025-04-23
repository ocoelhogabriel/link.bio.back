package br.com.ocoelhogabriel.link.bio.domain.dto.response;

import java.util.UUID;

import br.com.ocoelhogabriel.link.bio.domain.model.LinkBioModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LinkBioResponseDTO", description = "Dados do Link de Bio")
public class LinkBioResponseDTO extends LinkBioModel {

    @Schema(description = "Identificador dos dados dos Links (UUID)", example = "e4b1e302-f9c7-4ad3-bb8b-5a3e9a33fd2d", nullable = false)
    public UUID id;

    public LinkBioResponseDTO(UUID userId, String headerImageUrl, String title, String subtitle, String facebookUrl, String instagramUrl, String personalSiteUrl, String personalSiteText,
            String emailUrl, String whatsappUrl, UUID id) {
        super(userId, headerImageUrl, title, subtitle, facebookUrl, instagramUrl, personalSiteUrl, personalSiteText, emailUrl, whatsappUrl);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
