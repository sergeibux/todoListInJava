package groupe2.to_do_list.entity;

import javax.persistence.*;

@Entity
public class Role {

	public Role() {	}

	public Role(String nom) {
		this.nom = nom;
	}

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idRole;
    private String nom;

    public String toString() {
    	return "Role :> ID : " + this.idRole 
    + " :> Nom : " + nom; 
    }
}
