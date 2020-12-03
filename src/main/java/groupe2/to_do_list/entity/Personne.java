package groupe2.to_do_list.entity;

import javax.persistence.*;

@Entity
public class Personne {
	
	public Personne() {}
	
	public Personne(String nom, String prenom, String password, Role role) {
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
		this.password = password;
	}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_Personne;

    private String nom;
    private String prenom;
    private String password;

    @OneToOne
    private Role role;

    public String toString() {
    	return "Personne :> ID : " + this.id_Personne 
    + " :> Nom : " + nom 
    + " :> Prénom : " + prenom
    + " :> Mot de passe : " + password
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
