package Groupe2.To_do_list.Entity;

import Groupe2.To_do_list.Repository.PersonneRepository;
import javax.persistence.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Personne {

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
    
    public boolean savePersonne(String nom, String prenom, Role role, PersonneRepository personneRepository) {
	    try {
	        
	    	this.Nom = nom;
	    	this.Prenom = prenom;
	    	this.Role = role;
	    	
	    	personneRepository.save(this);
	        return true;
	    }catch (Exception e) {
	    	return false;
	    }
    }
}
