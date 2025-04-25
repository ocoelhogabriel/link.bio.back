package br.com.ocoelhogabriel.link.bio.domain.model;

import java.math.BigInteger;

import io.swagger.v3.oas.annotations.media.Schema;

public abstract class ServiceBioModel {

    @Schema(description = "Código do usuário dono do serviço")
    protected BigInteger userId;

    @Schema(description = "Slug ou referência para o link de bio", example = "gabriel.bio.com.br")
    protected String linkDeBio;

    protected ServiceBioModel(BigInteger userId, String linkDeBio) {
        super();
        this.userId = userId;
        this.linkDeBio = linkDeBio;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getLinkDeBio() {
        return linkDeBio;
    }

    public void setLinkDeBio(String linkDeBio) {
        this.linkDeBio = linkDeBio;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ServiceModel [");
        if (userId != null) {
            builder.append("userId=").append(userId).append(", ");
        }
        if (linkDeBio != null) {
            builder.append("linkDeBio=").append(linkDeBio);
        }
        builder.append("]");
        return builder.toString();
    }

}
