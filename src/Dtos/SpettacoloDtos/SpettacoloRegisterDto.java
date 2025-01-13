package Dtos.SpettacoloDtos;

import Entities.EnumKeyWords.SpettacoloEnums.Genere;

import java.sql.Time;

public record SpettacoloRegisterDto(
        Time orario,
        String luogo,
        Genere genere,
        String titolo
) {
}
