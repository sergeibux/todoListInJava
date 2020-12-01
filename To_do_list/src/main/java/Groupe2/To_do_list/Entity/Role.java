package Groupe2.To_do_list.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id_Role;

    private String Nom;


}
