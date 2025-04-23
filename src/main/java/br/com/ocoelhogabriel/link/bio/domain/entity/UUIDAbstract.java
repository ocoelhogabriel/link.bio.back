package br.com.ocoelhogabriel.link.bio.domain.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public abstract class UUIDAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    protected UUID id;

    protected UUIDAbstract(UUID id) {
        super();
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UUIDAbstract [");
        if (id != null) {
            builder.append("id=").append(id);
        }
        builder.append("]");
        return builder.toString();
    }

}
