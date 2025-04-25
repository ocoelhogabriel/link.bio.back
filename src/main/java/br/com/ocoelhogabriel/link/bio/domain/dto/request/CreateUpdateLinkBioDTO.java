package br.com.ocoelhogabriel.link.bio.domain.dto.request;

import java.math.BigInteger;

import br.com.ocoelhogabriel.link.bio.domain.entity.LinkBio;
import br.com.ocoelhogabriel.link.bio.domain.model.LinkBioModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LinkBioModel", description = "Dados para configurar o Link de Bio")
public class CreateUpdateLinkBioDTO extends LinkBioModel {

    public CreateUpdateLinkBioDTO(BigInteger userId, String headerImageUrl, String title, String subtitle, String facebookUrl, String instagramUrl, String personalSiteUrl, String personalSiteText,
            String emailUrl, String whatsappUrl) {
        super(userId, headerImageUrl, title, subtitle, facebookUrl, instagramUrl, personalSiteUrl, personalSiteText, emailUrl, whatsappUrl);
    }

    public LinkBio toEntity() {
        return new LinkBio(null, userId, getHeaderImageUrl(), getTitle(), getSubtitle(), getFacebookUrl(), getInstagramUrl(), getPersonalSiteUrl(), getPersonalSiteText(), getEmailUrl(),
                getWhatsappUrl());
    }

    public LinkBio toEntity(BigInteger id) {
        return new LinkBio(id, userId, getHeaderImageUrl(), getTitle(), getSubtitle(), getFacebookUrl(), getInstagramUrl(), getPersonalSiteUrl(), getPersonalSiteText(), getEmailUrl(),
                getWhatsappUrl());
    }
}
