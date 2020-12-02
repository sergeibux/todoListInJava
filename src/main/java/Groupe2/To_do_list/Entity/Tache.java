package Groupe2.To_do_list.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_Tache;

    private String titre;
    private String texte;
    private Date date_creation;
    private Date date_modification;

    @OneToOne
    private Personne personne;

    @OneToOne
    private Personne personne_creation;

    @OneToOne
    private Personne personne_Modification;

    @OneToOne
    private Status status;




}
