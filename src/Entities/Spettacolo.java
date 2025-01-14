package Entities;

import Entities.EnumKeyWords.SpettacoloEnums.Genere;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;

public class Spettacolo {
    private Integer id;
    private Time orario;
    private String luogo;
    private Integer prezzo;
    private Genere genere;
    private String titolo;
    private LocalDate data;
    private Duration durata;

    public Spettacolo() {
    }

    public Spettacolo(Time orario, String luogo, Integer prezzo, Genere genere, String titolo, LocalDate data, Duration durata) {
        this.orario = orario;
        this.luogo = luogo;
        this.prezzo = prezzo;
        this.genere = genere;
        this.titolo = titolo;
        this.data = data;
        this.durata = durata;
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

    public Integer getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Integer prezzo) {
        this.prezzo = prezzo;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Duration getDurata() {
        return durata;
    }

    public void setDurata(Duration durata) {
        this.durata = durata;
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
