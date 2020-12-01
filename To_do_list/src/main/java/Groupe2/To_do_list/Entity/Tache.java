package Groupe2.To_do_list.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id_Tache;

    private String Titre;
    private String Texte;
    private Date Date_creation;
    private Date Date_modification;

    @OneToOne
    private Personne personne;

    @OneToOne
    private Personne personne_creation;

    @OneToOne
    private Personne personne_Modification;

    /*
    @OneToOne
    private Status status;
    */




}
