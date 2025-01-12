package Services.SedeServices;

import Dtos.SedeDtos.SedeRegisterDto;
import Entities.Sede;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;
import Repositories.SedeDAO;

import java.sql.SQLException;
import java.util.List;

public class SedeServiceGeneralPurpose {
    SedeDAO sedeDAO = new SedeDAO();

    public SedeServiceGeneralPurpose() throws JDBCErrorConnectionException {
    }

    public Sede getBigliettoById(Integer id) throws SQLException, JDBCNoValueFieldException, ObjNotFoundException {
        sedeDAO.getSedeById(id);
        return null;
    }
    public List<Sede> getAllBiglietti() throws SQLException, JDBCNoValueFieldException, ObjNotFoundException {
        sedeDAO.getAllSedi();
        return null;
    }

    public void insertNewBiglietto(SedeRegisterDto sedeRegisterDto) throws SQLException {
        Sede biglietto = new Sede(
                sedeRegisterDto.nome(),
                sedeRegisterDto.indirizzo(),
                sedeRegisterDto.comune(),
                sedeRegisterDto.inside_outside(),
                sedeRegisterDto.id_sala()
        );
        sedeDAO.insertNewSede(biglietto);
    }
    public void deleteSedeById(Integer id) throws SQLException {
        sedeDAO.deleteSedeById(id);
    }
}
