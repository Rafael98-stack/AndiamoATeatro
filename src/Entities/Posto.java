package Entities;

import Entities.EnumKeyWords.PostoEnums.Availability;

public class Posto {
    private Integer id;
    private String fila;
    private Integer numero;
    private Availability available_unavailable;
    private Integer id_biglietto;

    public Posto() {
    }

    public Posto(String fila, Integer numero, Availability available_unavailable, Integer id_biglietto) {
        this.fila = fila;
        this.numero = numero;
        this.available_unavailable = available_unavailable;
        this.id_biglietto = id_biglietto;
    }

    public Posto(String fila, Integer numero, Integer id_biglietto) {
        this.fila = fila;
        this.numero = numero;
        this.id_biglietto = id_biglietto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Availability getAvailable_unavailable() {
        return available_unavailable;
    }

    public void setAvailable_unavailable(Availability available_unavailable) {
        this.available_unavailable = available_unavailable;
    }

    public Integer getId_biglietto() {
        return id_biglietto;
    }

    public void setId_biglietto(Integer id_biglietto) {
        this.id_biglietto = id_biglietto;
    }

    @Override
    public String toString() {
        return "Posto{" +
                "id=" + id +
                ", fila='" + fila + '\'' +
                ", numero=" + numero +
                ", available_unavailable=" + available_unavailable +
                ", id_biglietto=" + id_biglietto +
                '}';
    }
}
