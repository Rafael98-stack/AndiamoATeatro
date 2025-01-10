package Entities;

public class User extends Persona{
    private Integer id;

    public User() {
    }

    public User(Integer id, String nome, String cognome, String email, String indirizzo, String telefono) {
        super(nome, cognome, email, indirizzo, telefono);
        this.id = id;
    }

    public User(String nome, String cognome, String email, String indirizzo, String telefono) {
        super(nome, cognome, email, indirizzo, telefono);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public String getCognome() {
        return super.getCognome();
    }

    @Override
    public void setCognome(String cognome) {
        super.setCognome(cognome);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getIndirizzo() {
        return super.getIndirizzo();
    }

    @Override
    public void setIndirizzo(String indirizzo) {
        super.setIndirizzo(indirizzo);
    }

    @Override
    public String getTelefono() {
        return super.getTelefono();
    }

    @Override
    public void setTelefono(String telefono) {
        super.setTelefono(telefono);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                super.toString() +
                "}"
                ;
    }
}
