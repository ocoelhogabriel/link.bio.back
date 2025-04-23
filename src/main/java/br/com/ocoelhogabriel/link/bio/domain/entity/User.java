package br.com.ocoelhogabriel.link.bio.domain.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "users")
public class User extends UUIDAbstract implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Long idLogin;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@Email
	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;

	@Past
	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column
	private String phone;

	public User(UUID id, Long idLogin, String name, String email, LocalDate birthDate, String phone) {
		super(id);
		this.idLogin = idLogin;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.phone = phone;
	}

	public User(UUID id) {
		super(id);
		// Auto-generated constructor stub
	}

	public Long getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRepository [");
		if (idLogin != null) {
			builder.append("idLogin=").append(idLogin).append(", ");
		}
		if (name != null) {
			builder.append("name=").append(name).append(", ");
		}
		if (email != null) {
			builder.append("email=").append(email).append(", ");
		}
		if (birthDate != null) {
			builder.append("birthDate=").append(birthDate).append(", ");
		}
		if (phone != null) {
			builder.append("phone=").append(phone);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
