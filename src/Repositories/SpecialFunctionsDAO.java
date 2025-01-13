package Repositories;

import Configurations.JDBC;
import Entities.Biglietto;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

public class SpecialFunctionsDAO {
    Connection connection = JDBC.getConnection();
    BigliettoDAO bigliettoDAO = new BigliettoDAO();
    PostoDAO postoDAO = new PostoDAO();
    public SpecialFunctionsDAO() throws JDBCErrorConnectionException {
    }

    public void UserReservePosto( Biglietto biglietto) throws SQLException, ObjNotFoundException {

        Integer bigliettiMassimiPrenotabili = 4;
        if (bigliettoDAO.getBigliettoById(biglietto.getId_user()).getId_user() <= bigliettiMassimiPrenotabili){
            bigliettoDAO.insertNewBiglietto(biglietto);
        }

    }
}
