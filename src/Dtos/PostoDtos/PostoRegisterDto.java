package Dtos.PostoDtos;

import Entities.EnumKeyWords.PostoEnums.Availability;

public record PostoRegisterDto(
        String fila,
        Integer numero,
        Availability availability,
        Integer id_biglietto
) {
}
