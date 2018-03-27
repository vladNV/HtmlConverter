package model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSession {
    private static final SessionFactory factory;

    static {
        try {
            StandardServiceRegistry standardRegistry =
                    new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metaData =
                    new MetadataSources(standardRegistry).getMetadataBuilder().build();
            factory = metaData.getSessionFactoryBuilder().build();
        } catch (Throwable th) {

            System.err.println("Initial SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);

        }
    }

    public SessionFactory session() {
        return factory;
    }

    public void shutdown() {
        try {
            if (factory != null) {
                factory.close();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
