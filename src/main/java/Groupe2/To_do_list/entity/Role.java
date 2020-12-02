package Groupe2.To_do_list.entity;

import javax.persistence.*;

@Entity
public class Role {

	public Role() {	}

	public Role(String nom) {
		this.nom = nom;
	}

    public Integer getId_Role() {
        return id_Role;
    }

    public void setId_Role(Integer id_Role) {
        this.id_Role = id_Role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_Role;
    private String nom;

    public String toString() {
    	return "Role :> ID : " + this.id_Role 
    + " :> Nom : " + nom; 
    }
}
