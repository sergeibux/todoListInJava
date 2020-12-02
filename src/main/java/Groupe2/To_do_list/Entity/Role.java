package Groupe2.To_do_list.Entity;

import Groupe2.To_do_list.Repository.RoleRepository;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id_Role;

    private String Nom;

    public String to_string() {
    	return "Role :> ID : " + this.Id_Role 
    + " :> Nom : " + Nom; 
    }

    public boolean saveRole(String nom, RoleRepository roleRepository) {
        try {
            this.Nom = nom;
            roleRepository.save(this);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
