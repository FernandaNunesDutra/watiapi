package api.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-03T10:53:54")
@StaticMetamodel(Cigarette.class)
public class Cigarette_ { 

    public static volatile SingularAttribute<Cigarette, Long> economized;
    public static volatile SingularAttribute<Cigarette, Date> dateCreation;
    public static volatile SingularAttribute<Cigarette, Long> spent;
    public static volatile SingularAttribute<Cigarette, Integer> id;
    public static volatile SingularAttribute<Cigarette, Integer> userId;
    public static volatile SingularAttribute<Cigarette, Long> packCigarettesPrice;
    public static volatile SingularAttribute<Cigarette, Integer> numCigarette;

}