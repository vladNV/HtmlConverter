package model.service;

import model.HibernateApplication;
import model.domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class LoginService {

    public boolean authorisation(String login, String password) {
        HibernateApplication hibernate = new HibernateApplication();
        try (Session session = hibernate.session().openSession()){
           Transaction ts = session.beginTransaction();
           Query<User> query = session.createQuery(
                   "from User u where u.login=:login " +
                   "and u.password=:password", User.class);
           query.setParameter("login", login);
           query.setParameter("password", password);
           User u = query.uniqueResult();
           ts.commit();
           return u != null;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
