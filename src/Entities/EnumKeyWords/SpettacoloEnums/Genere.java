package Entities.EnumKeyWords.SpettacoloEnums;

import ExceptionHandlers.JDBCExceptions.JDBCNoValueFieldException;

public enum Genere {
    commedia,tragedia;
    public static Genere fromString(String value) throws JDBCNoValueFieldException {
        if (value == null) {
            throw  new JDBCNoValueFieldException("Error, Field has not Values.");
        }
        try {
            return Entities.EnumKeyWords.SpettacoloEnums.Genere.valueOf(value.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valore non valido per Genere: " + value, e);
        }
    }
}
