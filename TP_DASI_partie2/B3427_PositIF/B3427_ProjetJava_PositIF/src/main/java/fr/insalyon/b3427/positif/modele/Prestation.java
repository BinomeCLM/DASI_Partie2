package fr.insalyon.b3427.positif.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author B3427
 */
@Entity
public class Prestation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDebut;
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureFin;
    private Employe employe;
    private Client client;
    private Medium medium;
    public Prestation() {
    
    }
    public Long getId() {
        return id;
    }
    public Date getHeureDebut() {
        return heureDebut;
    }
    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }
    public Date getHeureFin() {
        return heureFin;
    }
    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Medium getMedium() {
        return medium;
    }
    public void setMedium(Medium medium) {
        this.medium = medium;
    }
}
