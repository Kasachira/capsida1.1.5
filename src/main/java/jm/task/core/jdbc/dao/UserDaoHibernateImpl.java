package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl  implements UserDao {
    private static  Session session;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
         this.session =  getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "ID BIGINT NOT NULL AUTO_INCREMENT, NAME TEXT(20), " +
                "LASTNAME TEXT(20), AGE TINYINT, PRIMARY KEY (ID) )";

        session.createSQLQuery(sql).executeUpdate();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        this.session =  getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        session.createSQLQuery(sql).executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        this.session =  getSessionFactory().openSession();
        session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        this.session =  getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        this.session =  getSessionFactory().openSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        this.session =  getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "DELETE FROM users";

        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
