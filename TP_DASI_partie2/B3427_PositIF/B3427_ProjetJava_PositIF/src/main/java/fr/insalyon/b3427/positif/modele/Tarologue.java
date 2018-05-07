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
public class Tarologue extends Medium implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cartes;
    public Tarologue(){
        super();
        talent = "Tarologue";
    }
    public Tarologue(String talent, String cartes) {
        super();
        this.talent = "Tarologue";
        this.cartes = cartes;
    }
    public String getCartes() {
        return cartes;
    }
    public void setCartes(String cartes) {
        this.cartes = cartes;
    }
}
