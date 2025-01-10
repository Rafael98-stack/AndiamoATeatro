package Repositories;

import Configurations.JDBC;
import Entities.User;
import ExceptionHandlers.UserExceptions.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    Connection connection = JDBC.getConnection();

public User getUserById(Integer id) throws SQLException, UserNotFoundException {
    User user = null;
    String query = "SELECT * FROM User WHERE id =?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1,id);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()){
        user = new User(
                resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getString("cognome"),
                resultSet.getString("email"),
                resultSet.getString("indirizzo"),
                resultSet.getString("telefono")
        );
        return user;
    }
   throw new UserNotFoundException("User non trovato.");
}
}
