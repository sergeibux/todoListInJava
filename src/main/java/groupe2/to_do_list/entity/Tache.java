package groupe2.to_do_list.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tache {

	public Tache() {}
	
	public Tache(
			String titre,
			String texte,
			Personne personne,
			Personne personne_creation,
			Status status) {
		this.titre = titre;
		this.texte = texte;
		this.date_creation = new java.util.Date().getTime();
		this.personne = personne;
		this.personne_creation = personne_creation;
		this.status = status;
	}
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_Tache;

    private String titre;
    private String texte;
    private long date_creation;
    private long date_modification;

    @OneToOne
    private Personne personne;

    @OneToOne
    private Personne personne_creation;

    @OneToOne
    private Personne personne_Modification;

    @OneToOne
    private Status status;


    public Integer getId_Tache() {
        return id_Tache;
    }

    public void setId_Tache(Integer id_Tache) {
        this.id_Tache = id_Tache;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Long getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(long date_creation) {
        this.date_creation = date_creation;
    }

    public Long getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(Long date_modification) {
        this.date_modification = date_modification;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Personne getPersonne_creation() {
        return personne_creation;
    }

    public void setPersonne_creation(Personne personne_creation) {
        this.personne_creation = personne_creation;
    }

    public Personne getPersonne_Modification() {
        return personne_Modification;
    }

    public void setPersonne_Modification(Personne personne_Modification) {
        this.personne_Modification = personne_Modification;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
