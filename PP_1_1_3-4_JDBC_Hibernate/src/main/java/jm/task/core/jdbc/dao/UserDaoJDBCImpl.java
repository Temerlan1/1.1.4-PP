package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getMyConnection()) {
            connection.setAutoCommit(false);
            connection.createStatement().execute(" CREATE TABLE IF NOT EXISTS myusers.users (id INT " +
                    " AUTO_INCREMENT PRIMARY KEY ,name VARCHAR(20) NOT NULL , lastname VARCHAR(20) NOT NULL," +
                    " age TINYINT UNSIGNED NOT NULL) ");
            connection.commit();
            System.out.println("Таблица создана!");
        } catch (SQLException e) {
            System.out.println("Таблица не создалась");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            connection = Util.getMyConnection();
            connection.setAutoCommit(false);
            connection.createStatement().execute(" DROP TABLE myusers.users");///  < - - - ОШИБКА ИЗ-ЗА РОЛЛБЭКа В СКОБКАХ
            connection.commit();
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {connection = Util.getMyConnection();
             PreparedStatement prStatement = connection.prepareStatement("INSERT INTO myusers.users (" +
                     "name, lastname, age) VALUES (?,?,?)");
            connection.setAutoCommit(false);
            prStatement.setString(1, name);
            prStatement.setString(2, lastName);
            prStatement.setByte(3, age);
            prStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeUserById(long id) {
        try {connection = Util.getMyConnection();
             PreparedStatement prStatement = connection.prepareStatement(" DELETE FROM myusers.users " +
                     "WHERE id= ?;");
            connection.setAutoCommit(false);
            prStatement.setLong(1, id);
            prStatement.executeUpdate();
            connection.commit();
            System.out.println("Строка с ИД = " + id + " удалена ");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.getMyConnection();
             PreparedStatement prStatement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = prStatement.executeQuery();
            ArrayList<User> userArrayList = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                userArrayList.add(user);
                System.out.println(user);
            }
            return userArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try { connection = Util.getMyConnection();
             PreparedStatement prStatement = connection.prepareStatement(" DELETE  FROM myusers.users");
            connection.setAutoCommit(false);
            prStatement.executeUpdate();
            System.out.println("Все строки очищены!");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
