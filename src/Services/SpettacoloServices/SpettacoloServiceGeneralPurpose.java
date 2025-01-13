package Services.SpettacoloServices;

import Dtos.SpettacoloDtos.SpettacoloRegisterDto;
import Dtos.SpettacoloDtos.SpettacoloUpdateDto;
import Entities.Spettacolo;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;
import Repositories.SpettacoloDAO;

import java.sql.SQLException;
import java.util.List;

public class SpettacoloServiceGeneralPurpose {
    SpettacoloDAO spettacoloDAO = new SpettacoloDAO();

    public SpettacoloServiceGeneralPurpose() throws JDBCErrorConnectionException {
    }

    public Spettacolo getSpettacoloById(Integer id) throws ObjNotFoundException, SQLException, JDBCNoValueFieldException {
        spettacoloDAO.getSpettacoloById(id);
        return null;
    }
    public List<Spettacolo> getAllSpettacolo() throws ObjNotFoundException, SQLException, JDBCNoValueFieldException {
        spettacoloDAO.getAllSpettacoli();
        return null;
    }

    public void registerSpettacolo(SpettacoloRegisterDto spettacoloRegisterDto) throws SQLException {
        Spettacolo spettacolo = new Spettacolo(
                spettacoloRegisterDto.orario(),
                spettacoloRegisterDto.luogo(),
                spettacoloRegisterDto.genere(),
                spettacoloRegisterDto.titolo()
        );
        spettacoloDAO.insertNewSpettacolo(spettacolo);
    }

    public void updateSpettacolo(SpettacoloUpdateDto spettacoloUpdateDto) throws SQLException {
        Spettacolo spettacolo = new Spettacolo(
                spettacoloUpdateDto.orario(),
                spettacoloUpdateDto.luogo(),
                spettacoloUpdateDto.genere(),
                spettacoloUpdateDto.titolo()
        );
        spettacolo.setId(spettacoloUpdateDto.id());
        spettacoloDAO.updateSpettacolo(spettacolo);
    }

    public void deleteSpettacoloById(Integer id) throws SQLException {
        spettacoloDAO.deleteSpettacoloById(id);
    }
}
