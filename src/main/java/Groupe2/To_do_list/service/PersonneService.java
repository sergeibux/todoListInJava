package Groupe2.To_do_list.service;

import Groupe2.To_do_list.entity.Role;
import Groupe2.To_do_list.repository.PersonneRepository;
import Groupe2.To_do_list.entity.Personne;

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
