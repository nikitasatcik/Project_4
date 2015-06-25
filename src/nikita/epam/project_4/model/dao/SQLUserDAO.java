package nikita.epam.project_4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nikita.epam.project_4.model.entity.User;

public class SQLUserDAO extends UserDAO {
    private static final String SQL_INSERT_USER = "INSERT INTO users " +
            "(email, password, firstName, surname,phone) VALUES (?, ?, ?, ?, ?)";

    @Override
    public int insertUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {

            // Get Connection and create PreparedStatement.
            connection = ConnectionPool.getConnection();

            assert connection != null;
            statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getPhone());
            return statement.executeUpdate();
        } catch (SQLException e) {
            return -1;
        } finally {
            super.closeStatement(statement);
            ConnectionPool.closeConnection(connection);
        }
    }

    @Override
    public User findUser(User user) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {

            // Get Connection and create PreparedStatement.
            connection = ConnectionPool.getConnection();

            statement = connection
                    .prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());

            resultSet = statement.executeQuery();

            if (resultSet.first()) {

                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("firstName"));
                user.setSurname(resultSet.getString("surname"));
                user.setPhone(resultSet.getString("phone"));
                int isActivated = resultSet.getInt("isActivated");
                user.setActivated((isActivated != 0));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("MySQLUserDAO.findUser() error");
        } finally {
            super.closeStatement(statement);
            ConnectionPool.closeConnection(connection);
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public Iterable<User> findUnconfirmed() {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        List<User> users = null;

        try {

            // Get Connection and create PreparedStatement.
            connection = ConnectionPool.getConnection();

            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery("SELECT * FROM users WHERE isActivated = 0");

            while (resultSet.next()) {
                if (users == null) {
                    users = new ArrayList<>();
                }
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("firstName"));
                user.setSurname(resultSet.getString("surname"));
                user.setPhone(resultSet.getString("phone"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("MySQLUserDAO.findUnconfirmed() error");
        } finally {
            super.closeStatement(statement);
            ConnectionPool.closeConnection(connection);
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public int activateBatch(Iterable<User> users) {
        Connection connection = null;
        PreparedStatement statement = null;

        if (users != null) {
            try {

                // Get Connection and create PreparedStatement.
                connection = ConnectionPool.getConnection();

                statement = connection.prepareStatement("UPDATE users SET isActivated = 1 WHERE id = ?");

                Iterator<User> iterator = users.iterator();

                while (iterator.hasNext()) {
                    statement.setInt(1, iterator.next().getId());
                    statement.addBatch();
                }
                int[] result = statement.executeBatch();
                int count = 0;

                for (int aResult : result) {
                    if (aResult == 1) {
                        ++count;
                    }
                }
                return count;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                super.closeStatement(statement);
                ConnectionPool.closeConnection(connection);
            }
        }
        return 0;
    }

    @Override
    public int deleteBatch(Iterable<User> users) {
        Connection connection = null;
        PreparedStatement statement = null;

        if (users != null) {
            try {

                // Get Connection and create Statement.
                connection = ConnectionPool.getConnection();

                statement = connection
                        .prepareStatement("DELETE FROM users WHERE id = ?");

                for (User user : users) {
                    statement.setInt(1, user.getId());
                    statement.addBatch();
                }

                int[] result = statement.executeBatch();
                int count = 0;

                for (int aResult : result) {
                    if (aResult == 1) {
                        ++count;
                    }
                }
                return count;

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                super.closeStatement(statement);
                ConnectionPool.closeConnection(connection);
            }
        }
        return 0;
    }
}