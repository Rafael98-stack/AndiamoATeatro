package Repositories;

import Configurations.JDBC;
import Entities.Biglietto;
import Entities.User;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.UserExceptions.UserNotFoundException;
import ExceptionHandlers.UserExceptions.UsersNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    Connection connection = JDBC.getConnection();

    public UserDAO() throws JDBCErrorConnectionException {
    }

    public User getUserById(Integer id) throws SQLException, UserNotFoundException {
    User user = null;
    String query = "SELECT * FROM User WHERE id =?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1,id);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()){
        System.out.println("User trovato.");
        user = new User(
                resultSet.getString("nome"),
                resultSet.getString("cognome"),
                resultSet.getString("email"),
                resultSet.getString("indirizzo"),
                resultSet.getString("telefono")
        );
        user.setId(resultSet.getInt("id"));
        return user;
    }
   throw new UserNotFoundException("User non trovato.");
}

    public List<User> getAllUsers() throws SQLException, UsersNotFoundException {
        List<User> users = new ArrayList<>();
        User user = null;
        String query = "SELECT * FROM User";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            while (resultSet.next()) {
                user = new User(
                        resultSet.getString("nome"),
                        resultSet.getString("cognome"),
                        resultSet.getString("email"),
                        resultSet.getString("indirizzo"),
                        resultSet.getString("telefono")
                );
                user.setId(resultSet.getInt("id"));
                users.add(user);
            }
            return users;
        }
        throw new UsersNotFoundException("Users non trovati.");
    }

    public void registerUser(User user) throws SQLException {
    String query = "INSERT INTO User (nome,cognome,email,indirizzo,telefono) VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user.getNome());
            preparedStatement.setString(2,user.getCognome());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getIndirizzo());
            preparedStatement.setString(5,user.getTelefono());
            preparedStatement.executeUpdate();
    }
    public void updateUser(User user) throws SQLException {
        String query = "UPDATE User SET nome = ?, cognome = ?, email = ?, indirizzo = ?, telefono = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getNome());
        preparedStatement.setString(2, user.getCognome());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getIndirizzo());
        preparedStatement.setString(5, user.getTelefono());
        preparedStatement.setInt(6,user.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteUserById(Integer id) throws SQLException {
        String query = "DELETE FROM User WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    public List<Biglietto> getAllBigliettiByUserId(Integer id_user) throws SQLException, ObjNotFoundException {
            String query = "SELECT u.id, b.* as biglietto_id FROM User u JOIN Biglietto b ON u.id = b.id_user WHERE u.id = ? ORDER BY ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id_user);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                List<Biglietto> biglietti = new ArrayList<>();
                while (resultSet.next()){
                    Biglietto biglietto = new Biglietto(
                            resultSet.getInt("id_user")
                    );
                    biglietto.setId(resultSet.getInt("id"));
                    biglietti.add(biglietto);
                }
                return biglietti;
            }
            throw new ObjNotFoundException("Object not Found or Empty.");
        }
    }

