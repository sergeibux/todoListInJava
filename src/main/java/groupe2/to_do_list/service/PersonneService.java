package groupe2.to_do_list.service;

import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.repository.PersonneRepository;
import groupe2.to_do_list.entity.Personne;

import java.util.Optional;

public class PersonneService {
    public static boolean savePersonne(String nom, String prenom, String password, Role role,
                                       PersonneRepository personneRepository) {
        try {
            Personne personne = new Personne(nom, prenom,password, role);

            personneRepository.save(personne);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updatePersonne(Personne personne, PersonneRepository personneRepository,
                                         String nom, String prenom, String password, Role role) {

        try {
            Optional<Personne> personneToUpdate = personneRepository.findById(personne.getId_Personne());
            if (personneToUpdate.isPresent()) {
                Personne personneUpdated = personneToUpdate.get();
                personneUpdated.setNom(nom);
                personneUpdated.setPrenom(prenom);
                personneUpdated.setRole(role);
                personneUpdated.setPassword(password);

                personneRepository.save(personne);

            }
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public static boolean checkIfPersonneIsAdmin(Personne personne) {

        Role personneRole = personne.getRole();
        return personneRole.getNom().equals("admin");
    }

    public static boolean deleteUserIfAdmin(Personne personne, String nom, String prenom, String password,
                                            Role role, PersonneRepository personneRepository) {
        Boolean isAdmin = checkIfPersonneIsAdmin(personne);
        if (isAdmin) {
            try {
                Personne personneToDelete = new Personne(nom, prenom, password, role);
                personneRepository.delete(personneToDelete);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
