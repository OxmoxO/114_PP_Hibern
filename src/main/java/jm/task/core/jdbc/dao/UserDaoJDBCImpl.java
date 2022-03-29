package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();
    private Statement statement = null;
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        try {
            statement = util.getConnection().createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Кыжмынки" +
                    "(ID BIGINT not null AUTO_INCREMENT, " +
                    "name VARCHAR(255), " +
                    "lastName VARCHAR(255), " +
                    "age TINYINT, " +
                    "PRIMARY KEY(ID))";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            statement.close();
        }
    }

    public void dropUsersTable() throws SQLException {
        try {
            statement = util.getConnection().createStatement();
            String sqlDelete = "DROP TABLE IF EXISTS Кыжмынки";
            statement.execute(sqlDelete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            statement.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            PreparedStatement preparedStatement = util.getConnection()
                    .prepareStatement("INSERT INTO Кыжмынки(name, lastName, age) VALUES(?,?,?)");
            preparedStatement.setString(1, name.trim());
            preparedStatement.setString(2, lastName.trim());
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с " + name + " –  добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            statement.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            statement = util.getConnection().createStatement();
            statement.execute("DELETE FROM Кыжмынки WHERE ID=" + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            statement.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        try {
            statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Кыжмынки");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("Name"),
                        resultSet.getString("LastName"),
                        resultSet.getByte("Age"));
                user.setId(resultSet.getLong("ID"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            statement.close();
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        try {
            statement = util.getConnection().createStatement();
            statement.execute("DELETE FROM Кыжмынки");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            statement.close();
        }
    }
}