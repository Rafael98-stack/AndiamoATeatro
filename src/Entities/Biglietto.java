package Entities;

public class Biglietto {
    private Integer id;
    private Integer id_user;

    public Biglietto() {
    }

    public Biglietto(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", id_user=" + id_user +
                '}';
    }
}
