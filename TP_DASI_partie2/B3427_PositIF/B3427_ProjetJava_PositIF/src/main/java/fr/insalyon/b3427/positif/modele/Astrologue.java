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
public class Astrologue extends Medium implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ecole;
    private String promotion;
    public Astrologue(){
        super();
        this.talent = "Astrologue";
    }
    public Astrologue(String ecole, String promotion) {
        super();
        this.talent = "Astrologue";
        this.ecole = ecole;
        this.promotion = promotion;
    }
    public String getEcole() {
        return ecole;
    }
    public void setEcole(String ecole) {
        this.ecole = ecole;
    }
    public String getPromotion() {
        return promotion;
    }
    public void setPromotion(String promotion) {
        this.promotion = promotion;
    } 
}
