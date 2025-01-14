package Services.SedeServices;

import Dtos.SedeDtos.SedeRegisterDto;
import Dtos.SedeDtos.SedeUpdateDto;
import Entities.Sala;
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

    public Sede getSedeById(Integer id) throws SQLException, JDBCNoValueFieldException, ObjNotFoundException {
        sedeDAO.getSedeById(id);
        return null;
    }

    public List<Sede> getAllSedi() throws SQLException, JDBCNoValueFieldException, ObjNotFoundException {
        sedeDAO.getAllSedi();
        return null;
    }

    public List<Sala> getAllSaleBySedeId(Integer id){
        return getAllSaleBySedeId(id);
    }

    public void insertNewSede(SedeRegisterDto sedeRegisterDto) throws SQLException {
        Sede biglietto = new Sede(
                sedeRegisterDto.nome(),
                sedeRegisterDto.indirizzo(),
                sedeRegisterDto.comune(),
                sedeRegisterDto.inside_outside(),
                sedeRegisterDto.id_sala()
        );
        sedeDAO.insertNewSede(biglietto);
    }

    public void updateSede(SedeUpdateDto sedeUpdateDto) throws SQLException {
        Sede sede = new Sede(
                sedeUpdateDto.nome(),
                sedeUpdateDto.indirizzo(),
                sedeUpdateDto.comune(),
                sedeUpdateDto.inside_outside(),
                sedeUpdateDto.id_sala()
        );
        sede.setId(sedeUpdateDto.id());
        sedeDAO.updateSede(sede);
    }
    public void deleteSedeById(Integer id) throws SQLException {
        sedeDAO.deleteSedeById(id);
    }
}
