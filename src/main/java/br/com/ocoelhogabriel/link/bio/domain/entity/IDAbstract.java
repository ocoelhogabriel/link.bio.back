package br.com.ocoelhogabriel.link.bio.domain.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IDAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private BigInteger id;

    protected IDAbstract(BigInteger id) {
        super();
        this.id = id;
    }

    protected IDAbstract() {
        super();
        // Auto-generated constructor stub
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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
