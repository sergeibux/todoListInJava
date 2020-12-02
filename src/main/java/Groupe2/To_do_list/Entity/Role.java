package Groupe2.To_do_list.Entity;

import javax.persistence.*;

@Entity
public class Role {

	public Role() {	}
	
	public Role(String nom) {
		this.Nom = nom;
	}
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id_Role;
    private String Nom;

    public String to_string() {
    	return "Role :> ID : " + this.Id_Role 
    + " :> Nom : " + Nom; 
    }
}
