package br.com.ocoelhogabriel.link.bio.domain.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "access")
public class Access extends UUIDAbstract {

    @Column(nullable = false)
    private UUID userId;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column
    private String token;

    public Access(UUID id, UUID userId, String login, String password, String token) {
        super(id);
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.token = token;
    }

    public Access(UUID id) {
        super(id);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
