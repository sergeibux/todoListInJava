package Groupe2.To_do_list.Entity;

import javax.persistence.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Personne {
	
	public Personne() {}
	
	public Personne(String nom, String prenom, Role role) {
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_Personne;

    private String nom;
    private String prenom;

    @OneToOne
    private Role role;

    public String to_string() {
    	return "Personne :> ID : " + this.id_Personne 
    + " :> Nom : " + nom 
    + " :> Prénom : " + prenom 
    + " :> Role : " + role.to_string(); 
    }
}
