package Repositories;

import Configurations.JDBC;
import Entities.EnumKeyWords.SpettacoloEnums.Genere;
import Entities.Spettacolo;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;

import java.sql.*;
import java.time.Duration;
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
                    Time.valueOf(resultSet.getString("orario")),
                    resultSet.getString("luogo"),
                    resultSet.getInt("prezzo"),
                    Genere.fromString(resultSet.getString("genere")),
                    resultSet.getString("titolo"),
                    resultSet.getDate("data").toLocalDate(),
                    Duration.ofMinutes(resultSet.getLong("durata"))
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
                        Time.valueOf(resultSet.getString("orario")),
                        resultSet.getString("luogo"),
                        resultSet.getInt("prezzo"),
                        Genere.fromString(resultSet.getString("genere")),
                        resultSet.getString("titolo"),
                        resultSet.getDate("data").toLocalDate(),
                        Duration.ofMinutes(resultSet.getLong("durata"))
                );
                spettacolo.setId(resultSet.getInt("id"));
                sale.add(spettacolo);
            }
        }
        throw new ObjNotFoundException("Objects not found or Empty.");
    }

    public void insertNewSpettacolo(Spettacolo spettacolo) throws SQLException {
        String query = "INSERT INTO Spettacolo (orario,luogo,prezzo,genere,titolo,data,durata) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTime(1, spettacolo.getOrario());
        preparedStatement.setString(2, spettacolo.getLuogo());
        preparedStatement.setInt(3, spettacolo.getPrezzo());
        preparedStatement.setString(4, spettacolo.getGenere().name());
        preparedStatement.setString(5, spettacolo.getTitolo());
        preparedStatement.setDate(6, Date.valueOf(spettacolo.getData()));
        preparedStatement.setLong(7, spettacolo.getDurata().getSeconds()/60);
        preparedStatement.executeUpdate();
    }
    public void updateSpettacolo(Spettacolo spettacolo) throws SQLException {
        String query = "UPDATE Spettacolo SET orario = ?, luogo = ?, prezzo = ?, genere = ?, titolo = ?, data = ?, durata = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTime(1, spettacolo.getOrario());
        preparedStatement.setString(2, spettacolo.getLuogo());
        preparedStatement.setInt(3, spettacolo.getPrezzo());
        preparedStatement.setString(4, spettacolo.getGenere().name());
        preparedStatement.setString(5, spettacolo.getTitolo());
        preparedStatement.setDate(6, Date.valueOf(spettacolo.getData()));
        preparedStatement.setLong(7, spettacolo.getDurata().getSeconds()/60);
        preparedStatement.setInt(8,spettacolo.getId());
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
