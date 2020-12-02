package Groupe2.To_do_list.Service;

import Groupe2.To_do_list.Repository.PersonneRepository;
import Groupe2.To_do_list.Entity.Role;
import Groupe2.To_do_list.Entity.Personne;

public class PersonneService{
	public static boolean savePersonne(String nom, String prenom, Role role, PersonneRepository personneRepository) {
	    try {
	    	Personne personne = new Personne(nom, prenom, role);
	    	
	    	personneRepository.save(personne);
	        return true;
	    }catch (Exception e) {
	    	return false;
	    }
	}
}
