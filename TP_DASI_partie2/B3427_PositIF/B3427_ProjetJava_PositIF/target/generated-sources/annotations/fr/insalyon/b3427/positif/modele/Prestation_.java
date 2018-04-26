package fr.insalyon.b3427.positif.modele;

import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Employe;
import fr.insalyon.b3427.positif.modele.Medium;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-24T15:03:13")
@StaticMetamodel(Prestation.class)
public class Prestation_ { 

    public static volatile SingularAttribute<Prestation, Employe> employe;
    public static volatile SingularAttribute<Prestation, Date> heureDebut;
    public static volatile SingularAttribute<Prestation, Client> client;
    public static volatile SingularAttribute<Prestation, Long> id;
    public static volatile SingularAttribute<Prestation, Medium> medium;
    public static volatile SingularAttribute<Prestation, Date> heureFin;

}