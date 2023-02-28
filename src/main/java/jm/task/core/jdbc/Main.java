package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main  {
    public static void main(String[] args) {
        Util.getConnection();
        UserServiceImpl createUsersTable = new UserServiceImpl();

        createUsersTable.createUsersTable();

        createUsersTable.saveUser("Name1", "LastName1", (byte) 20);
        createUsersTable.saveUser("Name2", "LastName2", (byte) 25);
        createUsersTable.saveUser("Name3", "LastName3", (byte) 31);
        createUsersTable.saveUser("Name4", "LastName4", (byte) 38);

        createUsersTable.removeUserById(1);
        createUsersTable.getAllUsers();
        createUsersTable.cleanUsersTable();
        createUsersTable.dropUsersTable();
    }
}
