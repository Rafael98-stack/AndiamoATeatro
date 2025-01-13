package Dtos.SalaDtos;

public record SalaUpdateDto(
        Integer id,
        String nome,
        Integer numero_posti,
        Integer id_posto,
        Integer id_spettacolo
) {
}
