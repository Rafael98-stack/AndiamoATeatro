package Dtos.UserDtos;

public record UserRegisterDto(
        String nome,
        String cognome,
        String email,
        String indirizzo,
        String telefono
) {
}
