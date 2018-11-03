package api.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-03T18:47:32")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Boolean> pesquisaEnviada;
    public static volatile SingularAttribute<User, Character> gender;
    public static volatile SingularAttribute<User, Date> dtCadastro;
    public static volatile SingularAttribute<User, Date> birth;
    public static volatile SingularAttribute<User, Integer> experimentalGroups;
    public static volatile SingularAttribute<User, String> preferedLanguage;
    public static volatile SingularAttribute<User, Boolean> receiveEmails;
    public static volatile SingularAttribute<User, String> token;
    public static volatile SingularAttribute<User, byte[]> password;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> recoverCode;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Boolean> authorizeData;

}