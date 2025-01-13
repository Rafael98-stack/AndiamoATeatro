package Services.BigliettoServices;

import Dtos.BigliettoDtos.BigliettoRegisterDto;
import Dtos.BigliettoDtos.BigliettoUpdateDto;
import Entities.Biglietto;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;
import ExceptionHandlers.PostoExceptions.PostiNotFoundException;
import ExceptionHandlers.PostoExceptions.PostoNotFoundException;
import Repositories.BigliettoDAO;

import java.sql.SQLException;
import java.util.List;

public class BigliettoServiceGeneralPurpose {
    BigliettoDAO bigliettoDAO = new BigliettoDAO();

    public BigliettoServiceGeneralPurpose() throws JDBCErrorConnectionException {
    }

    public Biglietto getBigliettoById(Integer id) throws SQLException, JDBCNoValueFieldException, PostoNotFoundException, ObjNotFoundException {
        bigliettoDAO.getBigliettoById(id);
        return null;
    }
    public List<Biglietto> getAllBiglietti() throws SQLException, PostiNotFoundException, JDBCNoValueFieldException, ObjNotFoundException {
        bigliettoDAO.getAllBiglietti();
        return null;
    }

    public void insertNewBiglietto(BigliettoRegisterDto bigliettoRegisterDto) throws SQLException {
        Biglietto biglietto = new Biglietto(
                bigliettoRegisterDto.id_user()
        );
        bigliettoDAO.insertNewBiglietto(biglietto);
    }

    public void updateBiglietto(BigliettoUpdateDto bigliettoUpdateDto) throws SQLException {
        Biglietto biglietto = new Biglietto(
                bigliettoUpdateDto.id_user()
        );
        biglietto.setId(bigliettoUpdateDto.id_user());
        bigliettoDAO.updateBiglietto(biglietto);
    }

    public void deleteBigliettoById(Integer id) throws SQLException {
        bigliettoDAO.deleteBigliettoById(id);
    }

    public static void main(String[] args) throws JDBCErrorConnectionException, SQLException {
        BigliettoServiceGeneralPurpose bigliettoServiceGeneralPurpose = new BigliettoServiceGeneralPurpose();
        BigliettoRegisterDto bigliettoRegisterDto = new BigliettoRegisterDto(1);
        bigliettoServiceGeneralPurpose.insertNewBiglietto(bigliettoRegisterDto);
    }
}
