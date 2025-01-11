package Dtos.UserDtos;

public record UserUpdateDto(
        Integer id,
        String nome,
        String cognome,
        String email,
        String indirizzo,
        String telefono
) {

}
