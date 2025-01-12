package Entities.EnumKeyWords.SedeEnums;

import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;

public enum Location {
    inside,outside;
    public static Location fromString(String value) throws JDBCNoValueFieldException {
        if (value == null) {
            throw  new JDBCNoValueFieldException("Error, Field has not Values.");
        }
        try {
            return Entities.EnumKeyWords.SedeEnums.Location.valueOf(value.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valore non valido per Location: " + value, e);
        }
    }
}
