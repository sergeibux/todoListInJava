package groupe2.to_do_list.service;

import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.repository.PersonneRepository;
import groupe2.to_do_list.entity.Personne;

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
