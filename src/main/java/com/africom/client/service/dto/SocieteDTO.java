package com.africom.client.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.africom.client.domain.Societe} entity.
 */
public class SocieteDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String raisonSociale;

    @NotNull
    private String raisonSocialeAbrege;

    @NotNull
    private String identifiantUnique;

    @NotNull
    private String registreCommerce;

    @NotNull
    private String codeArticle;

    @NotNull
    private String adresse;

    @NotNull
    private String codePostal;

    @NotNull
    private String pays;

    private String telephone;

    private String fax;

    @Pattern(regexp = "^[^@\\\\s]+@[^@\\\\s]+\\\\.[^@\\\\s]+$")
    private String email;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getRaisonSocialeAbrege() {
        return raisonSocialeAbrege;
    }

    public void setRaisonSocialeAbrege(String raisonSocialeAbrege) {
        this.raisonSocialeAbrege = raisonSocialeAbrege;
    }

    public String getIdentifiantUnique() {
        return identifiantUnique;
    }

    public void setIdentifiantUnique(String identifiantUnique) {
        this.identifiantUnique = identifiantUnique;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SocieteDTO)) {
            return false;
        }

        return id != null && id.equals(((SocieteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SocieteDTO{" +
            "id=" + getId() +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", raisonSocialeAbrege='" + getRaisonSocialeAbrege() + "'" +
            ", identifiantUnique='" + getIdentifiantUnique() + "'" +
            ", registreCommerce='" + getRegistreCommerce() + "'" +
            ", codeArticle='" + getCodeArticle() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", codePostal='" + getCodePostal() + "'" +
            ", pays='" + getPays() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
