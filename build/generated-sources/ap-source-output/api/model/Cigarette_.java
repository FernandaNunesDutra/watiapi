package api.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-04T20:54:20")
@StaticMetamodel(Cigarette.class)
public class Cigarette_ { 

    public static volatile SingularAttribute<Cigarette, Double> economized;
    public static volatile SingularAttribute<Cigarette, Date> dateCreation;
    public static volatile SingularAttribute<Cigarette, Double> spent;
    public static volatile SingularAttribute<Cigarette, Integer> id;
    public static volatile SingularAttribute<Cigarette, Long> userId;
    public static volatile SingularAttribute<Cigarette, Double> packCigarettesPrice;
    public static volatile SingularAttribute<Cigarette, Integer> numCigarette;

}