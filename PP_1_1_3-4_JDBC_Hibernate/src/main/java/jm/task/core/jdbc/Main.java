package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        //UserDaoJDBCImpl u =new UserDaoJDBCImpl();
        //Util.getSessionFactory().openSession();
        // u.createUsersTable();
        //u.saveUser("Ivan","Ivanov", (byte) 22);
        //u.saveUser("Petr","Petrov", (byte) 25);
        //u.saveUser("Sidor","Sidorov", (byte) 33);
        //u.saveUser("Temerlan","Utsiev", (byte) 22);
        //u.getAllUsers();
        //u.removeUserById(1);
        //u.cleanUsersTable();
        //u.dropUsersTable();
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
       dao.createUsersTable();
    //dao.saveUser("www","eee", (byte) 22);
    //dao.saveUser("aaa","eee", (byte) 23);
    //dao.saveUser("sss","eee", (byte) 24);
    //dao.saveUser("zzz","eee", (byte) 25);
    //dao.saveUser("ccc","eee", (byte) 26);
    //dao.removeUserById(3);
     dao.cleanUsersTable();
     // dao.dropUsersTable();

    }
}
