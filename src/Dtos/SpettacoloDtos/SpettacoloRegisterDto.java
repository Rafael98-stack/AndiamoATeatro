package Dtos.SpettacoloDtos;

import Entities.EnumKeyWords.SpettacoloEnums.Genere;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;

public record SpettacoloRegisterDto(
        Time orario,
        String luogo,
        Integer prezzo,
        Genere genere,
        String titolo,
        LocalDate data,
        Duration durata
) {
}
