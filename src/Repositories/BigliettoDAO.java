package Repositories;

import Configurations.JDBC;
import Entities.Biglietto;
import Entities.User;
import ExceptionHandlers.GeneralExceptionsTestings.NoOutputException;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.UserExceptions.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BigliettoDAO {
    Connection connection = JDBC.getConnection();

    public BigliettoDAO() throws JDBCErrorConnectionException {
    }

    public Biglietto getBigliettoById(Integer id) throws SQLException, ObjNotFoundException {
        String query = "SELECT * FROM Biglietto WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            Biglietto biglietto = new Biglietto(
                    resultSet.getInt("id_user")
            );
            biglietto.setId(resultSet.getInt("id"));
            return biglietto;
        }
        throw new ObjNotFoundException("Object not found or Empty");
    }

    public List<Biglietto> getAllBiglietti() throws SQLException, ObjNotFoundException {
        List<Biglietto> biglietti = new ArrayList<>();
        String query = "SELECT * FROM Biglietto";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            while (resultSet.next()){
                Biglietto biglietto = new Biglietto(
                        resultSet.getInt("id_user")
                );
                biglietto.setId(resultSet.getInt("id"));
                biglietti.add(biglietto);
            }
        }
      throw new ObjNotFoundException("Objects not found or Empty.");
    }

    public void insertNewBiglietto(Biglietto biglietto) throws SQLException {
        String query = "INSERT INTO Biglietto (id_user) VALUES(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, biglietto.getId_user());
        preparedStatement.executeUpdate();
    }
    public void updateBiglietto(Biglietto biglietto) throws SQLException {
        String query = "UPDATE Biglietto SET id_user = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, biglietto.getId());
        preparedStatement.setInt(2,biglietto.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteBigliettoById(Integer id) throws SQLException {
        String query = "DELETE FROM Biglietto WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        System.out.println("Il biglietto è stato cancellato correttamente.");
    }

    public Integer getBigliettoCountUser(Integer id) throws SQLException, NoOutputException {
        String query = "SELECT u.id AS user_id, COUNT(b.id) AS num_biglietti FROM Biglietto b JOIN User u ON b.id_user = u.id WHERE u.id = ? GROUP BY u.id";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getInt("num_biglietti");
        }
        throw new NoOutputException("Nessun Dato Trovato.");
    }

    public String getUserByBigliettoId(Integer id) throws UserNotFoundException, SQLException, ObjNotFoundException {
        String query = "SELECT u.*, b.id AS biglietto_id FROM Biglietto b JOIN User u on b.id_user = u.id WHERE b.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            User user = new User(
                    resultSet.getString("nome"),
                    resultSet.getString("cognome"),
                    resultSet.getString("email"),
                    resultSet.getString("indirizzo"),
                    resultSet.getString("telefono")
                    );
            user.setId(resultSet.getInt("id"));

            Biglietto biglietto = new Biglietto();
            biglietto.setId(resultSet.getInt("biglietto_id"));
            biglietto.setId_user(resultSet.getInt("id_user"));

            return String.format("UserBiglietto{" +
                    "id='%s', " +
                    "nome='%s', " +
                    "cognome='%s', " +
                    "email='%s', " +
                    "indirizzo='%s', " +
                    "telefono='%s', " +
                    "bigliettoId='%s'}",
                    user.getId(),
                    user.getNome(),
                    user.getCognome(),
                    user.getEmail(),
                    user.getIndirizzo(),
                    user.getTelefono(),
                    biglietto.getId()
                    );
        }
       throw new ObjNotFoundException("Object not Found or Empty.");
    }

    public static void main(String[] args) {

    }
}
