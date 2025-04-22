package br.com.ocoelhogabriel.domain.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class Services extends UUIDAbstract {

    @Column(nullable = false)
    private UUID userId;

    @Column(name = "link_de_bio", nullable = false)
    private String linkDeBio;

    public Services(UUID id, UUID userId, String linkDeBio) {
        super(id);
        this.userId = userId;
        this.linkDeBio = linkDeBio;
    }

    public Services(UUID id) {
        super(id);
        // Auto-generated constructor stub
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
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
        builder.append("ServicesRepository [");
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
