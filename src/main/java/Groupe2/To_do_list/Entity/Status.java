package Groupe2.To_do_list.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_Status;

    private String nom;

    public Integer getId_Status() {
        return id_Status;
    }

    public void setId_Status(Integer id_Status) {
        this.id_Status = id_Status;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
