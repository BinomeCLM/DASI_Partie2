package fr.insalyon.b3427.positif.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author B3427
 */
@Entity
public class Medium implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String bio;
    protected String talent;
    private int nbPrestation;
    public Medium() {
        this.nbPrestation=0;
    }
    public Long getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getBio() {
        return bio;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getTalent() {
        return talent;
    }
    public int getNbPrestation() {
        return nbPrestation;
    }
    public void setNbPrestation(int nbPrestation) {
        this.nbPrestation = nbPrestation;
    }
    public void incNbPrestation() {
        this.nbPrestation++;
    }
}
