package groupe2.to_do_list.service;

import groupe2.to_do_list.repository.TacheRepository;
import groupe2.to_do_list.entity.Tache;
import groupe2.to_do_list.entity.Personne;
import groupe2.to_do_list.entity.Status;

import java.time.LocalDateTime;
import java.util.Optional;

public class TacheService {
    public static boolean saveTache(
            String titre,
            String texte,
            Personne personne,
            Personne personneCreation,
            Status status,
            TacheRepository tacheRepository) {
        try {
            Tache tache = new Tache(titre, texte, personne, personneCreation, status);

            tacheRepository.save(tache);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updateTache(Tache tache, TacheRepository tacheRepository, String titre, String texte,
                                      LocalDateTime dateModification, Personne personneCreation, Status status) {

        try {
            Optional<Tache> tacheToUpdate = tacheRepository.findById(tache.getIdTache());
            if (tacheToUpdate.isPresent()) {
                Tache tacheUpdated = tacheToUpdate.get();
                tacheUpdated.setTitre(titre);
                tacheUpdated.setTexte(texte);
                tacheUpdated.setDateModification(dateModification);
                tacheUpdated.setPersonneCreation(personneCreation);
                tacheUpdated.setStatus(status);
            }

        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public static boolean deleteUser(int idTache, TacheRepository tacheRepository) {
        try {
            Tache t = tacheRepository.findById(idTache).get();
            if (t != null)
                tacheRepository.delete(t);

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
