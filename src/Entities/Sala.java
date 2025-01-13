package Entities;

public class Sala {
    private Integer id;
    private String nome;
    private Integer numero_posti;
    private Integer id_posto;
    private Integer id_spettacolo;

    public Sala() {
    }

    public Sala(String nome, Integer numero_posti, Integer id_posto, Integer id_spettacolo) {
        this.nome = nome;
        this.numero_posti = numero_posti;
        this.id_posto = id_posto;
        this.id_spettacolo = id_spettacolo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero_posti() {
        return numero_posti;
    }

    public void setNumero_posti(Integer numero_posti) {
        this.numero_posti = numero_posti;
    }

    public Integer getId_posto() {
        return id_posto;
    }

    public void setId_posto(Integer id_posto) {
        this.id_posto = id_posto;
    }

    public Integer getId_spettacolo() {
        return id_spettacolo;
    }

    public void setId_spettacolo(Integer id_spettacolo) {
        this.id_spettacolo = id_spettacolo;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", numero_posti=" + numero_posti +
                ", id_posto=" + id_posto +
                ", id_spettacolo=" + id_spettacolo +
                '}';
    }
}
