package Dtos.PostoDtos;

import Entities.EnumKeyWords.PostoEnums.Availability;

public record PostoUpdateDto(
        Integer id,
        String fila,
        Integer numero,
        Availability availability,
        Integer id_biglietto
) {

}
