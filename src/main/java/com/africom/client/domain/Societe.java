package com.africom.client.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Societe.
 */
@Entity
@Table(name = "societe")
public class Societe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "raison_sociale", nullable = false)
    private String raisonSociale;

    @NotNull
    @Column(name = "raison_sociale_abrege", nullable = false)
    private String raisonSocialeAbrege;

    @NotNull
    @Column(name = "identifiant_unique", nullable = false)
    private String identifiantUnique;

    @NotNull
    @Column(name = "registre_commerce", nullable = false)
    private String registreCommerce;

    @NotNull
    @Column(name = "code_article", nullable = false)
    private String codeArticle;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @NotNull
    @Column(name = "code_postal", nullable = false)
    private String codePostal;

    @NotNull
    @Column(name = "pays", nullable = false)
    private String pays;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "fax")
    private String fax;

    @Pattern(regexp = "^[^@\\\\s]+@[^@\\\\s]+\\\\.[^@\\\\s]+$")
    @Column(name = "email")
    private String email;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public Societe raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getRaisonSocialeAbrege() {
        return raisonSocialeAbrege;
    }

    public Societe raisonSocialeAbrege(String raisonSocialeAbrege) {
        this.raisonSocialeAbrege = raisonSocialeAbrege;
        return this;
    }

    public void setRaisonSocialeAbrege(String raisonSocialeAbrege) {
        this.raisonSocialeAbrege = raisonSocialeAbrege;
    }

    public String getIdentifiantUnique() {
        return identifiantUnique;
    }

    public Societe identifiantUnique(String identifiantUnique) {
        this.identifiantUnique = identifiantUnique;
        return this;
    }

    public void setIdentifiantUnique(String identifiantUnique) {
        this.identifiantUnique = identifiantUnique;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public Societe registreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
        return this;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public Societe codeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
        return this;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getAdresse() {
        return adresse;
    }

    public Societe adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public Societe codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public Societe pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTelephone() {
        return telephone;
    }

    public Societe telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public Societe fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public Societe email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Societe)) {
            return false;
        }
        return id != null && id.equals(((Societe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Societe{" +
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
