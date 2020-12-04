package groupe2.to_do_list.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idStatus;

    private String nom;

    public Status () {}
    
    public Status (String nom) {
     this.nom = nom;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setId_Status(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
