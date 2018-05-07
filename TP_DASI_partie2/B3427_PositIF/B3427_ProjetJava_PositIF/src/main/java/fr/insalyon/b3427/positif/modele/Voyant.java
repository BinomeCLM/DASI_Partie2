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
public class Voyant extends Medium implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String support;
    public Voyant(){
        super();
        this.talent = "Voyant";
    }
    public Voyant(String support) {
        super();
        this.talent = "Voyant";
        this.support = support;
    }
    public String getSupport() {
        return support;
    }
    public void setSupport(String support) {
        this.support = support;
    }
}
