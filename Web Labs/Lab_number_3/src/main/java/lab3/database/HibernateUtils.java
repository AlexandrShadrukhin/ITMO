package lab3.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import lab3.ResultBean;

import java.util.Properties;

public class HibernateUtils {
    private static final SessionFactory factory;

    static {
        try {
            Properties info = new Properties();
            info.load(HibernateUtils.class.getClassLoader().getResourceAsStream("/dataBase.cfg"));
            factory = new Configuration().configure()
                    .setProperty(AvailableSettings.USER,
                            info.getProperty("user"))
                    .setProperty(AvailableSettings.PASS,
                            info.getProperty("password"))
                    .addAnnotatedClass(ResultBean.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Something went wrong during initializing Hibernate: " + ex);
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
