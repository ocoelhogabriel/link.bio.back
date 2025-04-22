package br.com.ocoelhogabriel.domain.model;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public abstract class LinkBioModel {

    @Schema(description = "Identificador do usuário (UUID)", example = "e4b1e302-f9c7-4ad3-bb8b-5a3e9a33fd2d", nullable = false)
    protected UUID userId;

    @Schema(description = "URL da imagem do cabeçalho", example = "https://cdn.domain.com/images/header.png")
    protected String headerImageUrl;

    @Schema(description = "Título principal exibido no link", example = "Gabriel Coelho", nullable = false)
    protected String title;

    @Schema(description = "Subtítulo ou descrição complementar", example = "Desenvolvedor Full Stack")
    protected String subtitle;

    @Schema(description = "URL do perfil do Facebook", example = "https://facebook.com/gabriel.coelho")
    protected String facebookUrl;

    @Schema(description = "URL do perfil do Instagram", example = "https://instagram.com/gabrielcoelho.dev")
    protected String instagramUrl;

    @Schema(description = "URL de um site pessoal", example = "https://gabrielcoelho.dev")
    protected String personalSiteUrl;

    @Schema(description = "Texto a ser exibido para o link do site pessoal", example = "Meu portfólio")
    protected String personalSiteText;

    @Schema(description = "URL para e-mail direto (mailto)", example = "mailto:gabriel@email.com")
    protected String emailUrl;

    @Schema(description = "URL para contato via WhatsApp", example = "https://wa.me/5511999990000")
    protected String whatsappUrl;

    protected LinkBioModel(UUID userId, String headerImageUrl, String title, String subtitle, String facebookUrl, String instagramUrl, String personalSiteUrl, String personalSiteText, String emailUrl,
            String whatsappUrl) {
        super();
        this.userId = userId;
        this.headerImageUrl = headerImageUrl;
        this.title = title;
        this.subtitle = subtitle;
        this.facebookUrl = facebookUrl;
        this.instagramUrl = instagramUrl;
        this.personalSiteUrl = personalSiteUrl;
        this.personalSiteText = personalSiteText;
        this.emailUrl = emailUrl;
        this.whatsappUrl = whatsappUrl;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getHeaderImageUrl() {
        return headerImageUrl;
    }

    public void setHeaderImageUrl(String headerImageUrl) {
        this.headerImageUrl = headerImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getPersonalSiteUrl() {
        return personalSiteUrl;
    }

    public void setPersonalSiteUrl(String personalSiteUrl) {
        this.personalSiteUrl = personalSiteUrl;
    }

    public String getPersonalSiteText() {
        return personalSiteText;
    }

    public void setPersonalSiteText(String personalSiteText) {
        this.personalSiteText = personalSiteText;
    }

    public String getEmailUrl() {
        return emailUrl;
    }

    public void setEmailUrl(String emailUrl) {
        this.emailUrl = emailUrl;
    }

    public String getWhatsappUrl() {
        return whatsappUrl;
    }

    public void setWhatsappUrl(String whatsappUrl) {
        this.whatsappUrl = whatsappUrl;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LinkBioModel [");
        if (userId != null) {
            builder.append("userId=").append(userId).append(", ");
        }
        if (headerImageUrl != null) {
            builder.append("headerImageUrl=").append(headerImageUrl).append(", ");
        }
        if (title != null) {
            builder.append("title=").append(title).append(", ");
        }
        if (subtitle != null) {
            builder.append("subtitle=").append(subtitle).append(", ");
        }
        if (facebookUrl != null) {
            builder.append("facebookUrl=").append(facebookUrl).append(", ");
        }
        if (instagramUrl != null) {
            builder.append("instagramUrl=").append(instagramUrl).append(", ");
        }
        if (personalSiteUrl != null) {
            builder.append("personalSiteUrl=").append(personalSiteUrl).append(", ");
        }
        if (personalSiteText != null) {
            builder.append("personalSiteText=").append(personalSiteText).append(", ");
        }
        if (emailUrl != null) {
            builder.append("emailUrl=").append(emailUrl).append(", ");
        }
        if (whatsappUrl != null) {
            builder.append("whatsappUrl=").append(whatsappUrl);
        }
        builder.append("]");
        return builder.toString();
    }

}
