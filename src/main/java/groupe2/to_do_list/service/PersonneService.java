package groupe2.to_do_list.service;

import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.repository.PersonneRepository;
import groupe2.to_do_list.entity.Personne;
import groupe2.to_do_list.repository.RoleRepository;

import java.util.Optional;

public class PersonneService {
    public static boolean savePersonne(String nom, String prenom, String password, Role role,
                                       PersonneRepository personneRepository, RoleRepository roleRepository) {
        try {
            if (role == null) {
//TODO récupérer l'id réel dans la bdd
            	Optional<Role> role1 = roleRepository.findById(13);
                if (role1.isPresent()){
                    role = role1.get();
                }
            }
            Personne personne = new Personne(nom, prenom, password, role);

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

    public static boolean deleteUserIfAdmin(Personne personne, PersonneRepository personneRepository) {
        Boolean isAdmin = checkIfPersonneIsAdmin(personne);
        if (isAdmin) {
            try {
                Optional<Personne> personneToDelete = personneRepository.findById(personne.getId_Personne());
                if (personneToDelete.isPresent()) {
                    Personne personneDeleted = personneToDelete.get();

                    personneRepository.delete(personneDeleted);

                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    
    public static int getIdOnConnexion(String nom, String password, PersonneRepository personneRepository) {
    	Personne p = personneRepository.findByNomAndPasswordLike(nom, password);
    	
    	if (p == null)
    		return -1;
    	return p.getId_Personne();
    }

    public static boolean deleteUser(int id_personne, PersonneRepository personneRepository) {
        try {
        	Personne p = personneRepository.findById(id_personne).get();
        	if (p != null)
        		personneRepository.delete(p);
//            Optional<Personne> personneToDelete = personneRepository.findById(id_personne);
//            if (personneToDelete.isPresent()) {
//                Personne personneDeleted = personneToDelete.get();
//
//                personneRepository.delete(personneDeleted);
//
//            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
