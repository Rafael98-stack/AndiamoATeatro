package Entities;

import Entities.EnumKeyWords.SedeEnums.Location;

public class Sede {
    private Integer id;
    private String nome;
    private String indirizzo;
    private String comune;
    private Location inside_outside;
    private Integer id_sala;

    public Sede() {
    }

    public Sede(String nome, String indirizzo, String comune, Location inside_outside, Integer id_sala) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.comune = comune;
        this.inside_outside = inside_outside;
        this.id_sala = id_sala;
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public Location getInside_outside() {
        return inside_outside;
    }

    public void setInside_outside(Location inside_outside) {
        this.inside_outside = inside_outside;
    }

    public Integer getId_sala() {
        return id_sala;
    }

    public void setId_sala(Integer id_sala) {
        this.id_sala = id_sala;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", comune='" + comune + '\'' +
                ", inside_outside=" + inside_outside +
                ", id_sala=" + id_sala +
                '}';
    }
}
