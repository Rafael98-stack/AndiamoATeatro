package ExceptionHandlers.GeneralExceptionsTestings;

public class PostoIsAlreadyTakenException extends Exception{
    public PostoIsAlreadyTakenException(String message){
        super(message);
    }
}
