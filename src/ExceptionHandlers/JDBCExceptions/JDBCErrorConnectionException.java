package ExceptionHandlers.JDBCExceptions;

public class JDBCErrorConnectionException extends Exception{
    public JDBCErrorConnectionException(String message){
        super(message);
    }
}
