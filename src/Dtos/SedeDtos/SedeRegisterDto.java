package Dtos.SedeDtos;

import Entities.EnumKeyWords.SedeEnums.Location;

public record SedeRegisterDto(
        String nome,
        String indirizzo,
        String comune,
        Location inside_outside,
        Integer id_sala
) {
}
