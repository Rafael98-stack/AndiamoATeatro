package Dtos.SpecialFunctionDtos;

public record UserReservePostoDto(
        Integer id_user,
        Integer id_posto,
        Integer id_sala,
        Integer id_sede,
        Integer id_spettacolo,
        String fila,
        Integer numero
) {
}
