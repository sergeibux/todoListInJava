package groupe2.to_do_list.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tache {

	public Tache() {}
	
	public Tache(
			String titre,
			String texte,
			Date dateCreation,
			Personne personne,
			Personne personneCreation,
			Status status) {
		this.titre = titre;
		this.texte = texte;
		this.dateCreation = dateCreation;
		this.personne = personne;
		this.personneCreation = personneCreation;
		this.status = status;
	}
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idTache;

    private String titre;
    private String texte;
    private Date dateCreation;
    private Date dateModification;

    @OneToOne
    private Personne personne;

    @OneToOne
    private Personne personneCreation;

    @OneToOne
    private Personne personneModification;

    @OneToOne
    private Status status;


    public Integer getIdTache() {
        return idTache;
    }

    public void setIdTache(Integer idTache) {
        this.idTache = idTache;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Personne getPersonneCreation() {
        return personneCreation;
    }

    public void setPersonneCreation(Personne personneCreation) {
        this.personneCreation = personneCreation;
    }

    public Personne getPersonneModification() {
        return personneModification;
    }

    public void setPersonneModification(Personne personneModification) {
        this.personneModification = personneModification;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
