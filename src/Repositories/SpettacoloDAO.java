package Repositories;

import Configurations.JDBC;
import Entities.EnumKeyWords.SpettacoloEnums.Genere;
import Entities.Spettacolo;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;

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

    public Spettacolo getSpettacoloById(Integer id) throws SQLException, ObjNotFoundException, JDBCNoValueFieldException {
        String query = "SELECT * FROM Spettacolo WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            Spettacolo spettacolo = new Spettacolo(
                    resultSet.getTime("orario"),
                    resultSet.getString("luogo"),
                    Genere.fromString(resultSet.getString("id_posto")),
                    resultSet.getString("titolo")
            );
            spettacolo.setId(resultSet.getInt("id"));
            return spettacolo;
        }
        throw new ObjNotFoundException("Object not found or Empty");
    }

    public List<Spettacolo> getAllSpettacoli() throws SQLException, ObjNotFoundException, JDBCNoValueFieldException {
        List<Spettacolo> sale = new ArrayList<>();
        String query = "SELECT * FROM Spettacolo";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            while (resultSet.next()){
                Spettacolo spettacolo = new Spettacolo(
                        resultSet.getTime("orario"),
                        resultSet.getString("luogo"),
                        Genere.fromString(resultSet.getString("id_posto")),
                        resultSet.getString("titolo")
                );
                spettacolo.setId(resultSet.getInt("id"));
                sale.add(spettacolo);
            }
        }
        throw new ObjNotFoundException("Objects not found or Empty.");
    }

    public void insertNewSpettacolo(Spettacolo spettacolo) throws SQLException {
        String query = "INSERT INTO Spettacolo (orario,luogo,genere,titolo) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTime(1, spettacolo.getOrario());
        preparedStatement.setString(2, spettacolo.getLuogo());
        preparedStatement.setString(3, spettacolo.getGenere().name());
        preparedStatement.setString(4, spettacolo.getTitolo());
        preparedStatement.executeUpdate();
    }
    public void updateSpettacolo(Spettacolo spettacolo) throws SQLException {
        String query = "UPDATE Spettacolo SET nome = ?, numero_posti = ?, id_posto = ?, id_spettacolo = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTime(1, spettacolo.getOrario());
        preparedStatement.setString(2, spettacolo.getLuogo());
        preparedStatement.setString(3, spettacolo.getGenere().name());
        preparedStatement.setString(4, spettacolo.getTitolo());
        preparedStatement.setInt(5,spettacolo.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteSpettacoloById(Integer id) throws SQLException {
        String query = "DELETE FROM Spettacolo WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        System.out.println("Lo spettacolo è stato cancellato correttamente.");
    }
}
