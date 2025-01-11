package Services.PostoServices;

import Dtos.PostoDtos.PostoRegisterDto;
import Dtos.PostoDtos.PostoUpdateDto;
import Entities.Posto;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;
import ExceptionHandlers.PostoExceptions.PostiNotFoundException;
import ExceptionHandlers.PostoExceptions.PostoNotFoundException;
import Repositories.PostoDAO;

import java.sql.SQLException;
import java.util.List;

public class PostoServiceGeneralPurpose {
    PostoDAO postoDAO = new PostoDAO();

    public PostoServiceGeneralPurpose() throws JDBCErrorConnectionException {
    }

    public Posto getPostoById(Integer id) throws SQLException, JDBCNoValueFieldException, PostoNotFoundException {
        postoDAO.getPostoById(id);
        return null;
    }
    public List<Posto> getAllPosti() throws SQLException, PostiNotFoundException, JDBCNoValueFieldException {
        postoDAO.getAllPosti();
        return null;
    }

    public void insertNewPosto(PostoRegisterDto postoRegisterDto) throws SQLException {
        Posto posto = new Posto(
                postoRegisterDto.fila(),
                postoRegisterDto.numero(),
                postoRegisterDto.availability(),
                postoRegisterDto.id_biglietto()
        );
        postoDAO.insertNewPosto(posto);
    }

    public void updatePosto(PostoUpdateDto postoUpdateDto) throws SQLException {
        Posto posto = new Posto(
                postoUpdateDto.fila(),
                postoUpdateDto.numero(),
                postoUpdateDto.availability(),
                postoUpdateDto.id_biglietto()
        );
        posto.setId(postoUpdateDto.id());
        postoDAO.updatePosto(posto);
    }

    public void deletePostoById(Integer id) throws SQLException {
        postoDAO.deletePostoById(id);
    }
}
