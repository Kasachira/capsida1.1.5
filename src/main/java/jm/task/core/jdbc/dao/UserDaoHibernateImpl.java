package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = openTransactionSession();

        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "ID BIGINT NOT NULL AUTO_INCREMENT, NAME TEXT(20), " +
                "LASTNAME TEXT(20), AGE TINYINT, PRIMARY KEY (ID) )";

        session.createSQLQuery(sql).executeUpdate();
        closeSession();
    }

    @Override
    public void dropUsersTable() {
        Session session = openTransactionSession();

        String sql = "DROP TABLE IF EXISTS users";

        session.createSQLQuery(sql).executeUpdate();
        closeSession();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = openTransactionSession();

        User user = new User(name, lastName, age);
        session.save(user);

        closeTransactionSession();
    }

    @Override
    public void removeUserById(long id) {
        Session session = openTransactionSession();

        User user = session.get(User.class, id);
        session.delete(user);

        closeTransactionSession();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = openTransactionSession();

        List<User> users = session.createQuery("from User").getResultList();
        closeTransactionSession();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = openTransactionSession();

        String sql = "DELETE FROM users";

        session.createSQLQuery(sql).executeUpdate();

        closeTransactionSession();
    }
}
