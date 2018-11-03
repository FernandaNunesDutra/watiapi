package api.model;

import api.model.Tip;
import api.model.TipUserPK;
import api.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-03T18:47:32")
@StaticMetamodel(TipUser.class)
public class TipUser_ { 

    public static volatile SingularAttribute<TipUser, Date> date;
    public static volatile SingularAttribute<TipUser, Boolean> like;
    public static volatile SingularAttribute<TipUser, TipUserPK> tipUserPK;
    public static volatile SingularAttribute<TipUser, Tip> tip;
    public static volatile SingularAttribute<TipUser, User> user;

}