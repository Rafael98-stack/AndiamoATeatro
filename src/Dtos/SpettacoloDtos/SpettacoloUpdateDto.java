package Dtos.SpettacoloDtos;

import Entities.EnumKeyWords.SpettacoloEnums.Genere;

import java.sql.Time;

public record SpettacoloUpdateDto(
        Integer id,
        Time orario,
        String luogo,
        Genere genere,
        String titolo
) {
}
