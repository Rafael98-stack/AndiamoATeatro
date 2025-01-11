package ExceptionHandlers.UserExceptions;

public class UsersNotFoundException extends Exception{
    public UsersNotFoundException(String message){
        super(message);
    }
}
