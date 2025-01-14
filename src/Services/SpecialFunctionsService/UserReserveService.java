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
import Entities.Posto;
import Entities.Spettacolo;
import ExceptionHandlers.GeneralExceptionsTestings.MaxReservedTicket;
import ExceptionHandlers.GeneralExceptionsTestings.NoOutputException;
import ExceptionHandlers.GeneralExceptionsTestings.ObjNotFoundException;
import ExceptionHandlers.GeneralExceptionsTestings.PostoIsAlreadyTakenException;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;
import ExceptionHandlers.PostoExceptions.PostiNotFoundException;
import ExceptionHandlers.PostoExceptions.PostoNotFoundException;
import ExceptionHandlers.UserExceptions.UserNotFoundException;
import Repositories.BigliettoDAO;
import Repositories.PostoDAO;
import Repositories.SedeDAO;
import Repositories.UserDAO;
import Services.BigliettoServices.BigliettoServiceGeneralPurpose;
import Services.PostoServices.PostoServiceGeneralPurpose;
import Services.SalaServices.SalaServiceGeneralPurpose;
import Services.SedeServices.SedeServiceGeneralPurpose;
import Services.SpettacoloServices.SpettacoloServiceGeneralPurpose;
import Services.UserServices.UserServiceGeneralPurpose;

import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class UserReserveService {

    PostoServiceGeneralPurpose postoServiceGeneralPurpose = new PostoServiceGeneralPurpose();
    BigliettoServiceGeneralPurpose bigliettoServiceGeneralPurpose = new BigliettoServiceGeneralPurpose();
    UserServiceGeneralPurpose userServiceGeneralPurpose = new UserServiceGeneralPurpose();
    SedeServiceGeneralPurpose sedeServiceGeneralPurpose = new SedeServiceGeneralPurpose();

    PostoDAO postoDAO = new PostoDAO();
    BigliettoDAO bigliettoDAO = new BigliettoDAO();
    UserDAO userDAO = new UserDAO();
    SedeDAO sedeDAO = new SedeDAO();

    public UserReserveService() throws JDBCErrorConnectionException {
    }

    public void UserReservePosto (UserReservePostoDto userReservePosto) throws ObjNotFoundException, SQLException, PostiNotFoundException, JDBCNoValueFieldException, MaxReservedTicket, UserNotFoundException, PostoNotFoundException, PostoIsAlreadyTakenException, NoOutputException {

        if (bigliettoDAO.getBigliettoCountUser(userReservePosto.id_user()) == 4){
            System.out.println("Numero massimo di biglietti raggiunto.");
        if (bigliettoDAO.getBigliettoCountUser(userReservePosto.id_user()) > 4){
            throw new MaxReservedTicket("Numero massimo di biglietti prenotato.");
        }

        }

        BigliettoRegisterDto bigliettoRegisterDto = new BigliettoRegisterDto(
                userReservePosto.id_user()
        );

        Biglietto biglietto = new Biglietto(
                bigliettoRegisterDto.id_user()
        );

        bigliettoDAO.insertNewBiglietto(biglietto);
        List<Biglietto> biglietti = userDAO.getAllBigliettiByUserId(userReservePosto.id_user());


        PostoUpdateDto postoUpdateDto = new PostoUpdateDto(
                userReservePosto.id_posto(),
                userReservePosto.fila(),
                userReservePosto.numero(),
                Availability.unavailable,
                biglietti.getLast().getId()
        );

        if (postoDAO.getPostoById(userReservePosto.id_posto()).getAvailable_unavailable() == Availability.available){
            Posto posto = new Posto(
                    postoUpdateDto.fila(),
                    postoUpdateDto.numero(),
                    postoUpdateDto.availability(),
                    postoUpdateDto.id_biglietto()
            );
            postoDAO.updatePosto(posto);
        } else {
            throw new PostoIsAlreadyTakenException("Il Posto scelto è già prenotato.");
        }
    }

    public List<Spettacolo> getAllSpettacoliBySedeNome(String comune) throws ObjNotFoundException, SQLException, JDBCNoValueFieldException {
        return sedeDAO.getAllSpettacoliBySedeNome(comune);
    }

    public static void main(String[] args) throws JDBCErrorConnectionException, SQLException, ObjNotFoundException, JDBCNoValueFieldException, UserNotFoundException, PostoNotFoundException, PostiNotFoundException, NoOutputException, MaxReservedTicket, PostoIsAlreadyTakenException {
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
        postoServiceGeneralPurpose.insertNewPosto(postoRegisterDto);

        UserReservePostoDto userReservePostoDto = new UserReservePostoDto(1,1,1,"A",1);

        List<Spettacolo> spettacoli = userReserveService.getAllSpettacoliBySedeNome("Roma");
        spettacoli.forEach(System.out::println);
        userReserveService.UserReservePosto(userReservePostoDto);

        SpettacoloRegisterDto spettacoloRegisterDto = new SpettacoloRegisterDto(Time.valueOf("18:00:00"),"Via del Corso", 20, Genere.commedia,"La Commedia", LocalDate.of(2025,2,10), Duration.ofMinutes(60));
        spettacoloServiceGeneralPurpose.registerSpettacolo(spettacoloRegisterDto);

        SalaRegisterDto salaRegisterDto = new SalaRegisterDto("Gold",salaServiceGeneralPurpose.getAllPostiBySalaId(1).size(),postoServiceGeneralPurpose.getPostoById(1).getId(),spettacoloServiceGeneralPurpose.getSpettacoloById(1).getId());
        salaServiceGeneralPurpose.insertNewSala(salaRegisterDto);

        SedeRegisterDto sedeRegisterDto = new SedeRegisterDto("Il Cinema","Via Marmora","Roma", Location.inside,salaServiceGeneralPurpose.getSalaById(1).getId());
        sedeServiceGeneralPurpose.insertNewSede(sedeRegisterDto);

        // gggggggggggggggggg
    }
}
