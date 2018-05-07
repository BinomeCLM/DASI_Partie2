package fr.insalyon.b3427.positif.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author B3427
 */
@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String civilite;
    private String prenom;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String adressePostale;
    private String telephone;
    private String courriel;
    private String signeZodiaque;
    private String signeChinois;
    private String couleur;
    private String animalTotem;
    public Client() {
    }
    public Client(String civilite, String prenom, String nom, Date dateNaissance, String adressePostale, String telephone, String courriel) {
        this.civilite = civilite;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.adressePostale = adressePostale;
        this.telephone = telephone;
        this.courriel = courriel;
    }
    public Long getId() {
        return id;
    }
    public String getCivilite() {
        return civilite;
    }
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getAdressePostale() {
        return adressePostale;
    }
    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCourriel() {
        return courriel;
    }
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }
    public String getSigneZodiaque() {
        return signeZodiaque;
    }
    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }
    public String getSigneChinois() {
        return signeChinois;
    }
    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }
    public String getCouleur() {
        return couleur;
    }
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    public String getAnimalTotem() {
        return animalTotem;
    }
    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }
}
