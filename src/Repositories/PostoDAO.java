package Repositories;

import Configurations.JDBC;
import Entities.EnumKeyWords.PostoEnums.Availability;
import Entities.Posto;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;
import ExceptionHandlers.PostoExceptions.PostiNotFoundException;
import ExceptionHandlers.PostoExceptions.PostoNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostoDAO {
    Connection connection = JDBC.getConnection();

    public PostoDAO() throws JDBCErrorConnectionException {
    }

    public Posto getPostoById(Integer id) throws SQLException, JDBCNoValueFieldException, PostoNotFoundException {
        Posto posto = null;
        String query = "SELECT * FROM Posto WHERE id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("Posto trovato.");
            posto = new Posto(
                    resultSet.getString("fila"),
                    resultSet.getInt("numero"),
                    Availability.fromString(resultSet.getString("available_unavailable")),
                    resultSet.getInt("id_biglietto")
            );
            posto.setId(resultSet.getInt("id"));
            return posto;
        }
        throw new PostoNotFoundException("Posto non trovato.");
    }

    public List<Posto> getAllPosti() throws SQLException, JDBCNoValueFieldException, PostiNotFoundException {
        List<Posto> posti = new ArrayList<>();
        Posto posto = null;
        String query = "SELECT * FROM Posto";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            while (resultSet.next()) {
                posto = new Posto(
                        resultSet.getString("fila"),
                        resultSet.getInt("numero"),
                        Availability.fromString(resultSet.getString("available_unavailable")),
                        resultSet.getInt("id_biglietto")
                );
                posto.setId(resultSet.getInt("id"));
                posti.add(posto);
            }
            return posti;
        }
        throw new PostiNotFoundException("Posti non trovati.");
    }

    public void insertNewPosto(Posto posto) throws SQLException {
        String query = "INSERT INTO Posto (fila,numero,available_unavailable,id_biglietto) VALUES (?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,posto.getFila());
        preparedStatement.setInt(2,posto.getNumero());
        // Da ricordare che il metodo ".name()" degli Enum, converte il valore dell'Enum in Stringa.
        preparedStatement.setString(3,posto.getAvailable_unavailable().name());
        preparedStatement.setInt(4,posto.getId_biglietto());
        preparedStatement.executeUpdate();
    }
    public void updatePosto(Posto posto) throws SQLException {
        String query = "UPDATE User SET fila = ?, numero = ?, available_unavailable = ?, id_biglietto = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, posto.getFila());
        preparedStatement.setInt(2, posto.getNumero());
        preparedStatement.setString(3, posto.getAvailable_unavailable().name());
        preparedStatement.setInt(4, posto.getId_biglietto());
        preparedStatement.setInt(5,posto.getId());
        preparedStatement.executeUpdate();
    }

    public void deletePostoById(Integer id) throws SQLException {
        String query = "DELETE FROM Posto WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
}
