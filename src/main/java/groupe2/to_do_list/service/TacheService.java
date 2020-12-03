package groupe2.to_do_list.service;

import groupe2.to_do_list.repository.TacheRepository;
import groupe2.to_do_list.entity.Tache;
import groupe2.to_do_list.entity.Personne;
import groupe2.to_do_list.entity.Status;
import java.util.Date;

public class TacheService{
	public static boolean saveTache(
			String titre,
			String texte,
			Date date_creation,
			Personne personne,
			Personne personne_creation,
			Status status,
			TacheRepository tacheRepository) {
	    try {
	    	Tache tache = new Tache (titre, texte, date_creation, personne, personne_creation, status);
	    	
	    	tacheRepository.save(tache);
	        return true;
	    }catch (Exception e) {
	    	return false;
	    }
	}
}
