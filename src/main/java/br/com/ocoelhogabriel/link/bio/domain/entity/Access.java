package br.com.ocoelhogabriel.link.bio.domain.entity;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ocoelhogabriel.link.bio.domain.enuns.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "access")
public class Access extends IDAbstract implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private BigInteger userId;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column
    private String token;

    @Column
    private UserRole role;

    public Access(BigInteger id, BigInteger userId, @NotBlank String login, @NotBlank String password, String token, UserRole role) {
        super(id);
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.token = token;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        // Auto-generated method stub
        return this.password;
    }

    @Override
    public String getUsername() {
        // Auto-generated method stub
        return this.login;
    }

    public static Access createAccessDefault(BigInteger userId, String passwordEncoder) {
        Access accessDefault = new Access(null);
        accessDefault.setLogin("admin");
        accessDefault.setPassword(passwordEncoder);
        accessDefault.setUserId(userId);
        accessDefault.setRole(UserRole.ADMIN);
        accessDefault.setToken(null);
        return accessDefault;
    }

    public static Access updateAccessDefault(Access accessDefault, BigInteger userId, String passwordEncoder) {
        accessDefault.setLogin("admin");
        accessDefault.setPassword(passwordEncoder);
        accessDefault.setUserId(userId);
        accessDefault.setRole(UserRole.ADMIN);
        accessDefault.setToken(null);
        return accessDefault;
    }

    public Access(BigInteger id) {
        super(id);
    }

    public Access() {
        super();
        // Auto-generated constructor stub
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    // public String getPassword() {
    // return password;
    // }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}
