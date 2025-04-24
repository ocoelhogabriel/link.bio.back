package br.com.ocoelhogabriel.link.bio.domain.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "link_bio")
public class LinkBio extends UUIDAbstract {

    @Column(nullable = false)
    private UUID userId;

    @Column(name = "header_image_url")
    private String headerImageUrl;

    @Column(nullable = false)
    private String title;

    @Column
    private String subtitle;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "personal_site_url")
    private String personalSiteUrl;

    @Column(name = "personal_site_text")
    private String personalSiteText;

    @Column(name = "email_url")
    private String emailUrl;

    @Column(name = "whatsapp_url")
    private String whatsappUrl;

    public LinkBio(UUID id, UUID userId, String headerImageUrl, String title, String subtitle, String facebookUrl, String instagramUrl, String personalSiteUrl, String personalSiteText,
            String emailUrl, String whatsappUrl) {
        super(id);
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

    public LinkBio(UUID id) {
        super(id);
    }

    public LinkBio() {
        super();
        // Auto-generated constructor stub
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
        builder.append("LinkBioRepository [");
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
