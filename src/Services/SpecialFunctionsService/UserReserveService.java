package Services.SpecialFunctionsService;

import Dtos.BigliettoDtos.BigliettoRegisterDto;
import Dtos.PostoDtos.PostoRegisterDto;
import Dtos.PostoDtos.PostoUpdateDto;
import Dtos.SalaDtos.SalaRegisterDto;
import Dtos.SedeDtos.SedeRegisterDto;
import Dtos.SpecialFunctionDtos.UserReservePostoDto;
import Dtos.SpettacoloDtos.SpettacoloRegisterDto;
import Dtos.UserDtos.UserRegisterDto;
import Entities.Biglietto;
import Entities.EnumKeyWords.PostoEnums.Availability;
import Entities.EnumKeyWords.SedeEnums.Location;
import Entities.EnumKeyWords.SpettacoloEnums.Genere;
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
import Services.SedeServices.SedeServiceGeneralPurpose;
import Services.SpettacoloServices.SpettacoloServiceGeneralPurpose;
import Services.UserServices.UserServiceGeneralPurpose;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class UserReserveService {

    PostoServiceGeneralPurpose postoServiceGeneralPurpose = new PostoServiceGeneralPurpose();
    BigliettoServiceGeneralPurpose bigliettoServiceGeneralPurpose = new BigliettoServiceGeneralPurpose();
    UserServiceGeneralPurpose userServiceGeneralPurpose = new UserServiceGeneralPurpose();
    public UserReserveService() throws JDBCErrorConnectionException {
    }

    public void UserReservePosto (UserReservePostoDto userReservePosto) throws ObjNotFoundException, SQLException, PostiNotFoundException, JDBCNoValueFieldException, MaxReservedTicket, UserNotFoundException, PostoNotFoundException, PostoIsAlreadyTakenException, NoOutputException {





        if (bigliettoServiceGeneralPurpose.getBigliettoCountUser(userReservePosto.id_user()) == 4){
            System.out.println("Numero massimo di biglietti raggiunto.");
        if (bigliettoServiceGeneralPurpose.getBigliettoCountUser(userReservePosto.id_user()) > 4){
            throw new MaxReservedTicket("Numero massimo di biglietti prenotato.");
        }
        }
        BigliettoRegisterDto bigliettoRegisterDto = new BigliettoRegisterDto(
                userReservePosto.id_user()
        );

        bigliettoServiceGeneralPurpose.insertNewBiglietto(bigliettoRegisterDto);
        List<Biglietto> biglietti = userServiceGeneralPurpose.getAllBigliettiByUserId(userReservePosto.id_user());


        PostoUpdateDto postoUpdateDto = new PostoUpdateDto(
                userReservePosto.id_posto(),
                userReservePosto.fila(),
                userReservePosto.numero(),
                Availability.unavailable,
                biglietti.get(biglietti.size()-1).getId()
        );

        if (postoServiceGeneralPurpose.getPostoById(userReservePosto.id_posto()).getAvailable_unavailable() == Availability.available){
            postoServiceGeneralPurpose.updatePosto(postoUpdateDto);
        } else {
            throw new PostoIsAlreadyTakenException("Il Posto scelto è già prenotato.");
        }
    }

    public static void main(String[] args) throws JDBCErrorConnectionException, SQLException, ObjNotFoundException, JDBCNoValueFieldException, UserNotFoundException, PostoNotFoundException {
        UserServiceGeneralPurpose userServiceGeneralPurpose = new UserServiceGeneralPurpose();
        PostoServiceGeneralPurpose postoServiceGeneralPurpose = new PostoServiceGeneralPurpose();
        BigliettoServiceGeneralPurpose bigliettoServiceGeneralPurpose = new BigliettoServiceGeneralPurpose();
        SpettacoloServiceGeneralPurpose spettacoloServiceGeneralPurpose = new SpettacoloServiceGeneralPurpose();
        SedeServiceGeneralPurpose sedeServiceGeneralPurpose = new SedeServiceGeneralPurpose();
        SalaServiceGeneralPurpose salaServiceGeneralPurpose = new SalaServiceGeneralPurpose();

        UserReserveService userReserveService = new UserReserveService();

        UserRegisterDto userRegisterDto = new UserRegisterDto("Marco","Fragnoli","marcofragnoli@gmail.com","Via Bulgaria","3452445679");
        userServiceGeneralPurpose.registerUser(userRegisterDto);

        BigliettoRegisterDto bigliettoRegisterDto = new BigliettoRegisterDto(userServiceGeneralPurpose.getUserById(1).getId());

        PostoRegisterDto postoRegisterDto = new PostoRegisterDto("A",1,Availability.available,bigliettoServiceGeneralPurpose.getBigliettoById(1).getId());

        SpettacoloRegisterDto spettacoloRegisterDto = new SpettacoloRegisterDto(Time.valueOf("18:00:00"),"Via del Corso", Genere.commedia,"La Commedia");
        spettacoloServiceGeneralPurpose.registerSpettacolo(spettacoloRegisterDto);

        SalaRegisterDto salaRegisterDto = new SalaRegisterDto("Gold",salaServiceGeneralPurpose.getAllPostiBySalaId(1).size(),postoServiceGeneralPurpose.getPostoById(1).getId(),spettacoloServiceGeneralPurpose.getSpettacoloById(1).getId());
        salaServiceGeneralPurpose.insertNewSala(salaRegisterDto);

        SedeRegisterDto sedeRegisterDto = new SedeRegisterDto("Il Cinema","Via Marmora","Roma", Location.inside,salaServiceGeneralPurpose.getSalaById(1).getId());
        sedeServiceGeneralPurpose.insertNewSede(sedeRegisterDto);

        // gggggggggggggggggg
    }
}
