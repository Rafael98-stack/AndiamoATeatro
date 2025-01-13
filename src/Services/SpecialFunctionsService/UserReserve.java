package Services.SpecialFunctionsService;

import Dtos.PostoDtos.PostoUpdateDto;
import Dtos.SpecialFunctionDtos.UserReservePostoDto;
import Entities.Biglietto;
import Entities.EnumKeyWords.PostoEnums.Availability;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;
import ExceptionHandlers.PostoExceptions.PostiNotFoundException;
import Repositories.SpecialFunctionsDAO;
import Services.BigliettoServices.BigliettoServiceGeneralPurpose;
import Services.PostoServices.PostoServiceGeneralPurpose;

import java.sql.SQLException;
import java.util.List;

public class UserReserve {


    PostoServiceGeneralPurpose postoServiceGeneralPurpose = new PostoServiceGeneralPurpose();
    BigliettoServiceGeneralPurpose bigliettoServiceGeneralPurpose = new BigliettoServiceGeneralPurpose();
    SpecialFunctionsDAO specialFunctionsDAO = new SpecialFunctionsDAO();
    public UserReserve() throws JDBCErrorConnectionException {
    }

    public void UserReservePosto (UserReservePostoDto userReservePosto) throws ObjNotFoundException, SQLException, PostiNotFoundException, JDBCNoValueFieldException {
        Biglietto biglietto = new Biglietto(userReservePosto.id_user());
      PostoUpdateDto postoUpdateDto = new PostoUpdateDto(userReservePosto.id_posto(), userReservePosto.fila(), userReservePosto.numero(), Availability.unavailable, biglietto.getId());
        postoServiceGeneralPurpose.updatePosto(postoUpdateDto);
        List<Biglietto> biglietti = bigliettoServiceGeneralPurpose.getAllBiglietti();

        //Posto posto = new Posto(userRevservePosto.fila(), userRevservePosto.numero(), Availability.unavailable,biglietto.getId());
        //postoDAO.updatePosto(posto);
        specialFunctionsDAO.UserReservePosto(biglietto);
    }
}
