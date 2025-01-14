package Repositories;

import Configurations.JDBC;
import Entities.Sala;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {
    Connection connection = JDBC.getConnection();

    public SalaDAO() throws JDBCErrorConnectionException {
    }

    public Sala getSalaById(Integer id) throws SQLException, ObjNotFoundException {
        String query = "SELECT * FROM Sala WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            Sala sala = new Sala(
                    resultSet.getString("nome"),
                    resultSet.getInt("numero_posti"),
                    resultSet.getInt("id_posto"),
                    resultSet.getInt("id_spettacolo")
            );
            sala.setId(resultSet.getInt("id"));
            return sala;
        }
        throw new ObjNotFoundException("Object not found or Empty");
    }

    public List<Sala> getAllSale() throws SQLException, ObjNotFoundException {
        List<Sala> sale = new ArrayList<>();
        String query = "SELECT * FROM Sala";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            while (resultSet.next()){
                Sala sala = new Sala(
                        resultSet.getString("nome"),
                        resultSet.getInt("numero_posti"),
                        resultSet.getInt("id_posto"),
                        resultSet.getInt("id_spettacolo")
                );
                sala.setId(resultSet.getInt("id"));
                sale.add(sala);
            }
        }
        throw new ObjNotFoundException("Objects not found or Empty.");
    }

    public void insertNewSala(Sala sala) throws SQLException {
        String query = "INSERT INTO Sala (nome,numero_posti,id_posto,id_spettacolo) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, sala.getNome());
        preparedStatement.setInt(2, sala.getNumero_posti());
        preparedStatement.setInt(3, sala.getId_posto());
        preparedStatement.setInt(4, sala.getId_spettacolo());
        preparedStatement.executeUpdate();
    }

    public void updateSala(Sala sala) throws SQLException {
        String query = "UPDATE Sala SET nome = ?, numero_posti = ?, id_posto = ?, id_spettacolo = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, sala.getNome());
        preparedStatement.setInt(2,sala.getNumero_posti());
        preparedStatement.setInt(3,sala.getId_posto());
        preparedStatement.setInt(4,sala.getId_spettacolo());
        preparedStatement.setInt(5,sala.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteSalaById(Integer id) throws SQLException {
        String query = "DELETE FROM Sala WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        System.out.println("La sala è stata cancellata correttamente.");
    }
}
