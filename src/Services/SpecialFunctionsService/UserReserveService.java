package Services.SpecialFunctionsService;

import Dtos.BigliettoDtos.BigliettoRegisterDto;
import Dtos.PostoDtos.PostoUpdateDto;
import Dtos.SalaDtos.SalaUpdateDto;
import Dtos.SpecialFunctionDtos.UserReservePostoDto;
import Entities.Biglietto;
import Entities.EnumKeyWords.PostoEnums.Availability;
import Entities.Posto;
import Entities.Sala;
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
import Services.SalaServices.SalaServiceGeneralPurpose;

import java.sql.SQLException;
import java.util.List;

public class UserReserveService {

    PostoServiceGeneralPurpose postoServiceGeneralPurpose = new PostoServiceGeneralPurpose();
    BigliettoServiceGeneralPurpose bigliettoServiceGeneralPurpose = new BigliettoServiceGeneralPurpose();
    SalaServiceGeneralPurpose salaServiceGeneralPurpose = new SalaServiceGeneralPurpose();

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

        Sala sala = salaServiceGeneralPurpose.getSalaById(
                userReservePosto.id_sala()
        );
/*
        List<Biglietto> biglietti = bigliettoServiceGeneralPurpose.getAllBiglietti();
        Integer count = 0;
        Integer currentBiglietto = 0;

        while (count < 5){
            if (biglietti.get(currentBiglietto).getId_user() == userReservePosto.id_user()){
                count++;
            }
            if (count == 4){
                throw new MaxReservedTicket("Numero massimo di biglietti Prenotati");
            }
                currentBiglietto++;
        }
*/
        if (bigliettoServiceGeneralPurpose.getBigliettoCountUser(userReservePosto.id_user()) == 4){
            throw new MaxReservedTicket("Numero massimo di biglietti prenotato.");
        }

        bigliettoServiceGeneralPurpose.insertNewBiglietto(bigliettoRegisterDto);

        List<Posto> posti = postoServiceGeneralPurpose.getAllPosti();
        Integer currentPosto = 0;
        while ( currentPosto < posti.size()-1){

            if (posti.get(userReservePosto.id_posto()).getId_biglietto() == posti.get(currentPosto).getId_biglietto()){
                throw new  PostoIsAlreadyTakenException("Il posto è già riservato");
            }
            currentPosto++;
        }

        postoServiceGeneralPurpose.updatePosto(postoUpdateDto);

        SalaUpdateDto salaUpdateDto = new SalaUpdateDto(
                userReservePosto.id_sala(),
                sala.getNome(),
                sala.getNumero_posti(),
                postoServiceGeneralPurpose.getPostoById(userReservePosto.id_posto()).getId(),
                userReservePosto.id_spettacolo()
                );

        salaServiceGeneralPurpose.updateSala(salaUpdateDto);


    }
}
