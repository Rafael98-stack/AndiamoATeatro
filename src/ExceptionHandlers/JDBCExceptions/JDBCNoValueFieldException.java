package ExceptionHandlers.JDBCExceptions;

public class JDBCNoValueFieldException extends Exception{
    public JDBCNoValueFieldException(String message){
        super(message);
    }
}
