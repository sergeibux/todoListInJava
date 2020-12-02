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
    + " :> Role : " + role.toString();
    }

    public Integer getId_Personne() {
        return id_Personne;
    }

    public void setId_Personne(Integer id_Personne) {
        this.id_Personne = id_Personne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
