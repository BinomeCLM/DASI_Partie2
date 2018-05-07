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
public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomEmploye;
    private String talent;
    private boolean busy;
    private int nbPrestation;
    
    public Employe() {
        this.busy=false;
        this.nbPrestation=0;
    }

    public Long getId() {
        return id;
    }
    
    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }
    
    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
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
