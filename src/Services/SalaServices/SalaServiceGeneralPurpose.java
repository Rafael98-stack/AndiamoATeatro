package Services.SalaServices;

import Dtos.SalaDtos.SalaRegisterDto;
import Dtos.SalaDtos.SalaUpdateDto;
import Entities.Sala;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.UserExceptions.UserNotFoundException;
import ExceptionHandlers.UserExceptions.UsersNotFoundException;
import Repositories.SalaDAO;

import java.sql.SQLException;
import java.util.List;

public class SalaServiceGeneralPurpose {
    SalaDAO salaDAO = new SalaDAO();

    public SalaServiceGeneralPurpose() throws JDBCErrorConnectionException {
    }

    public Sala getSalaById(Integer id) throws UserNotFoundException, SQLException, ObjNotFoundException {
        salaDAO.getSalaById(id);
        return null;
    }
    public List<Sala> getAllUser() throws UsersNotFoundException, SQLException, ObjNotFoundException {
        salaDAO.getAllSale();
        return null;
    }

    public void registerUser(SalaRegisterDto salaRegisterDto) throws SQLException {
        Sala sala = new Sala(
                salaRegisterDto.nome(),
                salaRegisterDto.numero_posti(),
                salaRegisterDto.id_posto(),
                salaRegisterDto.id_spettacolo()
        );
        salaDAO.insertNewSala(sala);
    }

    public void updateSala(SalaUpdateDto salaUpdateDto) throws SQLException {
        Sala sala = new Sala(
                salaUpdateDto.nome(),
                salaUpdateDto.numero_posti(),
                salaUpdateDto.id_posto(),
                salaUpdateDto.id_spettacolo()
        );
        sala.setId(salaUpdateDto.id());
        salaDAO.updateSala(sala);
    }

    public void deleteSalaById(Integer id) throws SQLException {
        salaDAO.deleteSalaById(id);
    }
}
