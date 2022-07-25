package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/myusers";
    private static final String USERNAME = "Temerlan";
    private static final String PASSWORD = "2345t";
    private static SessionFactory sessionFactory;
    private static Configuration configuration= new org.hibernate.cfg.Configuration()
            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
            .setProperty("hibernate.connection.username", "Temerlan")
            .setProperty("hibernate.connection.password", "2345t")
            .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/myusers");

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
             return configuration.addAnnotatedClass(User.class).buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Connection getMyConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


}
