package Entities;

import Entities.EnumKeyWords.SpettacoloEnums.Genere;

import java.sql.Time;

public class Spettacolo {
    private Integer id;
    private Time orario;
    private String luogo;
    private Genere genere;
    private String titolo;

    public Spettacolo() {
    }

    public Spettacolo(Time orario, String luogo, Genere genere, String titolo) {
        this.orario = orario;
        this.luogo = luogo;
        this.genere = genere;
        this.titolo = titolo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getOrario() {
        return orario;
    }

    public void setOrario(Time orario) {
        this.orario = orario;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public String toString() {
        return "Spettacolo{" +
                "id=" + id +
                ", orario=" + orario +
                ", luogo='" + luogo + '\'' +
                ", genere=" + genere +
                ", titolo='" + titolo + '\'' +
                '}';
    }
}
