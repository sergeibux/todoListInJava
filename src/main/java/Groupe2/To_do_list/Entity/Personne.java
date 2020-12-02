package Groupe2.To_do_list.Entity;

import javax.persistence.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Personne {
	
	public Personne() {}
	
	public Personne(String nom, String prenom, Role role) {
		this.Nom = nom;
		this.Prenom = prenom;
		this.Role = role;
	}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id_Personne;

    private String Nom;
    private String Prenom;

    @OneToOne
    private Role Role;

    public String to_string() {
    	return "Personne :> ID : " + this.Id_Personne 
    + " :> Nom : " + Nom 
    + " :> Prénom : " + Prenom 
    + " :> Role : " + Role.to_string(); 
    }
}
