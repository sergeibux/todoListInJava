package groupe2.to_do_list.service;

import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.repository.PersonneRepository;
import groupe2.to_do_list.repository.TacheRepository;
import groupe2.to_do_list.entity.Tache;
import groupe2.to_do_list.entity.Personne;
import groupe2.to_do_list.entity.Status;

import java.util.Date;
import java.util.Optional;

public class TacheService {
    public static boolean saveTache(
            String titre,
            String texte,
            Date date_creation,
            Personne personne,
            Personne personne_creation,
            Status status,
            TacheRepository tacheRepository) {
        try {
            Tache tache = new Tache(titre, texte, date_creation, personne, personne_creation, status);

            tacheRepository.save(tache);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updateTache(Tache tache, TacheRepository tacheRepository, String titre, String texte,
                                      Date dateModification, Personne personneCreation, Status status, Role role) {

        try {
            Optional<Tache> tacheToUpdate = tacheRepository.findById(tache.getId_Tache());
            if (tacheToUpdate.isPresent()) {
                Tache tacheUpdated = tacheToUpdate.get();
                tacheUpdated.setTitre(titre);
                tacheUpdated.setTexte(texte);
                tacheUpdated.setDate_modification(dateModification);
                tacheUpdated.setPersonne_creation(personneCreation);
                tacheUpdated.setStatus(status);
            }

        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public static boolean deleteUser(int id_tache, TacheRepository tacheRepository) {
        try {
            Tache t = tacheRepository.findById(id_tache).get();
            if (t != null)
                tacheRepository.delete(t);

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
