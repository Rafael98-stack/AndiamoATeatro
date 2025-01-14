package Repositories;

import Configurations.JDBC;
import Entities.EnumKeyWords.SedeEnums.Location;
import Entities.Sala;
import Entities.Sede;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SedeDAO {
    Connection connection = JDBC.getConnection();

    public SedeDAO() throws JDBCErrorConnectionException {
    }

    public Sede getSedeById(Integer id) throws SQLException, JDBCNoValueFieldException, ObjNotFoundException {
        Sede sede = null;
        String query = "SELECT * FROM Sede WHERE id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("Sede trovato.");
            sede = new Sede(
                    resultSet.getString("nome"),
                    resultSet.getString("indirizzo"),
                    resultSet.getString("comune"),
                    Location.fromString(resultSet.getString("inside_outside")),
                    resultSet.getInt("id_sala")
            );
            sede.setId(resultSet.getInt("id"));
            return sede;
        }
        throw new ObjNotFoundException("Sede non trovato.");
    }

    public List<Sede> getAllSedi() throws SQLException, JDBCNoValueFieldException, ObjNotFoundException {
        List<Sede> posti = new ArrayList<>();
        Sede sede = null;
        String query = "SELECT * FROM Sede";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            while (resultSet.next()) {
                sede = new Sede(
                        resultSet.getString("nome"),
                        resultSet.getString("indirizzo"),
                        resultSet.getString("comune"),
                        Location.fromString(resultSet.getString("inside_outside")),
                        resultSet.getInt("id_sala")
                );
                sede.setId(resultSet.getInt("id"));
                posti.add(sede);
            }
            return posti;
        }
        throw new ObjNotFoundException("Sedi non trovati.");
    }

    public List<Sala> getAllSaleBySedeId(Integer id) throws ObjNotFoundException, SQLException {
        String query = "SELECT sa.*, se.id AS id_sede FROM Sede se JOIN Sala sa ON se.id_sala = sa.id WHERE se.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            List<Sala> sale = new ArrayList<>();
            while (resultSet.next()){
                Sala sala = new Sala(
                        resultSet.getString("nome"),
                        resultSet.getInt("numero_posti"),
                        resultSet.getInt("id_posto"),
                        resultSet.getInt("id_spettacolo")
                );
                sale.add(sala);
            }
            return sale;
        }
        throw new ObjNotFoundException("Nessuna Sala trovata");
    }

    public void insertNewSede(Sede sede) throws SQLException {
        String query = "INSERT INTO Sede (nome,indirizzo,comune,inside_outside,id_sala) VALUES (?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,sede.getNome());
        preparedStatement.setString(2,sede.getIndirizzo());
        preparedStatement.setString(3,sede.getComune());
        preparedStatement.setString(4,sede.getInside_outside().name());
        preparedStatement.setInt(5,sede.getId_sala());
        preparedStatement.executeUpdate();
    }
    public void updateSede(Sede sede) throws SQLException {
        String query = "UPDATE Sede SET nome = ?, indirizzo = ?, comune = ?, inside_outside = ?, id_sala = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,sede.getNome());
        preparedStatement.setString(2,sede.getIndirizzo());
        preparedStatement.setString(3,sede.getComune());
        preparedStatement.setString(4,sede.getInside_outside().name());
        preparedStatement.setInt(5,sede.getId_sala());
        preparedStatement.setInt(6,sede.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteSedeById(Integer id) throws SQLException {
        String query = "DELETE FROM Sede WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
}
