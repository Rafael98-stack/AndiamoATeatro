package Dtos.SedeDtos;

import Entities.EnumKeyWords.SedeEnums.Location;

public record SedeUpdateDto(
        Integer id,
        String nome,
        String indirizzo,
        String comune,
        Location inside_outside,
        Integer id_sala
) {

}
