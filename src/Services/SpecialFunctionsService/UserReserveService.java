package Services.SpecialFunctionsService;

import Dtos.BigliettoDtos.BigliettoRegisterDto;
import Dtos.PostoDtos.PostoUpdateDto;
import Dtos.SpecialFunctionDtos.UserReservePostoDto;
import Entities.Biglietto;
import Entities.EnumKeyWords.PostoEnums.Availability;
import ExceptionHandlers.GeneralExceptionsTestings.MaxReservedTicket;
import ExceptionHandlers.GeneralExceptionsTestings.NoOutputException;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.GeneralExceptionsTestings.PostoIsAlreadyTakenException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;
import ExceptionHandlers.PostoExceptions.PostiNotFoundException;
import ExceptionHandlers.PostoExceptions.PostoNotFoundException;
import ExceptionHandlers.UserExceptions.UserNotFoundException;
import Services.BigliettoServices.BigliettoServiceGeneralPurpose;
import Services.PostoServices.PostoServiceGeneralPurpose;

import java.sql.SQLException;

public class UserReserveService {

    PostoServiceGeneralPurpose postoServiceGeneralPurpose = new PostoServiceGeneralPurpose();
    BigliettoServiceGeneralPurpose bigliettoServiceGeneralPurpose = new BigliettoServiceGeneralPurpose();

    public UserReserveService() throws JDBCErrorConnectionException {
    }

    public void UserReservePosto (UserReservePostoDto userReservePosto) throws ObjNotFoundException, SQLException, PostiNotFoundException, JDBCNoValueFieldException, MaxReservedTicket, UserNotFoundException, PostoNotFoundException, PostoIsAlreadyTakenException, NoOutputException {

        Biglietto biglietto = new Biglietto(
                userReservePosto.id_user()
        );

        PostoUpdateDto postoUpdateDto = new PostoUpdateDto(
                userReservePosto.id_posto(),
                userReservePosto.fila(),
                userReservePosto.numero(),
                Availability.unavailable,
                biglietto.getId()
        );

        BigliettoRegisterDto bigliettoRegisterDto = new BigliettoRegisterDto(
                userReservePosto.id_user()
        );

        if (bigliettoServiceGeneralPurpose.getBigliettoCountUser(userReservePosto.id_user()) == 4){
            System.out.println("Numero massimo di biglietti raggiunto.");
        if (bigliettoServiceGeneralPurpose.getBigliettoCountUser(userReservePosto.id_user()) > 4){
            throw new MaxReservedTicket("Numero massimo di biglietti prenotato.");
        }
        }

        bigliettoServiceGeneralPurpose.insertNewBiglietto(bigliettoRegisterDto);


        if (postoServiceGeneralPurpose.postoIsAlredyAssigned(userReservePosto.id_posto()) == null){
            postoServiceGeneralPurpose.updatePosto(postoUpdateDto);
        }

    }
}
