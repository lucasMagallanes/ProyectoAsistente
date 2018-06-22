package dataAccess;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Clase ConectorSingleton.
 * @author Hector
 *
 */
public final class ConectorSingleton {

    private static ConectorSingleton instance = new ConectorSingleton();
    private Session session;
/**
 * Conector Singleton.
 */
    private ConectorSingleton() {
        // TODO Auto-generated constructor stub
    }
/**
 * Getter de Instance.
 * @return instance
 */
    public static ConectorSingleton getInstance() {
        return instance;
    }
/**
 * Getter de Session.
 * @return session
 */
    public Session getSession() {

        if (session == null) {
            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
        }

        return session;

    }

}
