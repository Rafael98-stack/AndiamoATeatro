package Services.UserServices;

import Dtos.UserDtos.UserUpdateDto;
import Dtos.UserDtos.UserRegisterDto;
import Entities.User;
import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;
import ExceptionHandlers.UserExceptions.UserNotFoundException;
import ExceptionHandlers.UserExceptions.UsersNotFoundException;
import Repositories.UserDAO;

import java.sql.SQLException;
import java.util.List;

public class UserServiceGeneralPurpose {
    UserDAO userDAO = new UserDAO();

    public UserServiceGeneralPurpose() throws JDBCErrorConnectionException {
    }

    public User getUserById(Integer id) throws UserNotFoundException, SQLException {
        userDAO.getUserById(id);
        return null;
    }
    public List<User> getAllUser() throws UsersNotFoundException, SQLException {
        userDAO.getAllUsers();
        return null;
    }

    public void registerUser(UserRegisterDto userRegisterDto) throws SQLException {
        User user = new User(
                userRegisterDto.nome(),
                userRegisterDto.cognome(),
                userRegisterDto.email(),
                userRegisterDto.indirizzo(),
                userRegisterDto.telefono()
        );
        userDAO.registerUser(user);
    }

    public void updateUser(UserUpdateDto userUpdateDto) throws SQLException {
        User user = new User(
                userUpdateDto.nome(),
                userUpdateDto.cognome(),
                userUpdateDto.email(),
                userUpdateDto.indirizzo(),
                userUpdateDto.telefono()
        );
        user.setId(userUpdateDto.id());
        userDAO.updateUser(user);
    }

    public void deleteUserById(Integer id) throws SQLException {
        userDAO.deleteUserById(id);
    }
}
