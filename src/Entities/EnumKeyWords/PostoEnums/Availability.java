package Entities.EnumKeyWords.PostoEnums;

import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;

public enum Availability {
    available,unavailable;
    public static Availability fromString(String value) throws JDBCNoValueFieldException {
        if (value == null) {
           throw  new JDBCNoValueFieldException("Error, Field has not Values.");
        }
        try {
            return Availability.valueOf(value.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valore non valido per Availability: " + value, e);
        }
    }
}
