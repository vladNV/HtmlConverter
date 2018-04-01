package model.service;

import model.HibernateApplication;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseService {

    public void save(Object entity) {
        HibernateApplication hibernate = new HibernateApplication();
        try (Session session = hibernate.session().openSession()) {
            Transaction ts = session.beginTransaction();
            session.save(entity);
            ts.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
