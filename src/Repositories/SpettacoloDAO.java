package Repositories;

import Configurations.JDBC;
import Entities.Spettacolo;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpettacoloDAO {
    Connection connection = JDBC.getConnection();

    public SpettacoloDAO() throws JDBCErrorConnectionException {
    }

    public Spettacolo getSpettacoloById(Integer id) throws SQLException, ObjNotFoundException {
        String query = "SELECT * FROM Spettacolo WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            Spettacolo spettacolo = new Spettacolo(
                    resultSet.getTime("orario"),
                    resultSet.getInt("numero_posti"),
                    resultSet.getInt("id_posto"),
                    resultSet.getInt("id_spettacolo")
            );
            spettacolo.setId(resultSet.getInt("id"));
            return spettacolo;
        }
        throw new ObjNotFoundException("Object not found or Empty");
    }

    public List<Spettacolo> getAllSpettacoli() throws SQLException, ObjNotFoundException {
        List<Spettacolo> sale = new ArrayList<>();
        String query = "SELECT * FROM Spettacolo";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            while (resultSet.next()){
                Spettacolo spettacolo = new Spettacolo(
                        resultSet.getString("nome"),
                        resultSet.getInt("numero_posti"),
                        resultSet.getInt("id_posto"),
                        resultSet.getInt("id_spettacolo")
                );
                spettacolo.setId(resultSet.getInt("id"));
                sale.add(spettacolo);
            }
        }
        throw new ObjNotFoundException("Objects not found or Empty.");
    }

    public void insertNewSpettacolo(Spettacolo spettacolo) throws SQLException {
        String query = "INSERT INTO Spettacolo (nome,numero_posti,id_posto,id_spettacolo) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, spettacolo.getNome());
        preparedStatement.setInt(2, spettacolo.getNumero_posti());
        preparedStatement.setInt(3, spettacolo.getId_posto());
        preparedStatement.setInt(4, spettacolo.getId_spettacolo());
        preparedStatement.executeUpdate();
    }
    public void updateSpettacolo(Spettacolo spettacolo) throws SQLException {
        String query = "UPDATE Spettacolo SET nome = ?, numero_posti = ?, id_posto = ?, id_spettacolo = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, spettacolo.getNome());
        preparedStatement.setInt(2,spettacolo.getNumero_posti());
        preparedStatement.setInt(3,spettacolo.getId_posto());
        preparedStatement.setInt(4,spettacolo.getId_spettacolo());
        preparedStatement.setInt(5,spettacolo.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteSpettacoloById(Integer id) throws SQLException {
        String query = "DELETE FROM Spettacolo WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        System.out.println("La spettacolo è stata cancellata correttamente.");
    }
}
