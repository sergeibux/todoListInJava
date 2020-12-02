package Groupe2.To_do_list.Entity;

import javax.persistence.*;

@Entity
public class Role {

	public Role() {	}
	
	public Role(String nom) {
		this.nom = nom;
	}
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_Role;
    private String nom;

    public String to_string() {
    	return "Role :> ID : " + this.id_Role 
    + " :> Nom : " + nom; 
    }
}
