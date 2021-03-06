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
            	Optional<Role> role1 = roleRepository.findById(1);
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
            Optional<Personne> personneToUpdate = personneRepository.findById(personne.getIdPersonne());
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
                Optional<Personne> personneToDelete = personneRepository.findById(personne.getIdPersonne());
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
    	return p.getIdPersonne();
    }

    public static boolean deleteUser(int idPersonne, PersonneRepository personneRepository) {
        try {
        	Personne p = personneRepository.findById(idPersonne).get();
        	if (p != null)
        		personneRepository.delete(p);
//            Optional<Personne> personneToDelete = personneRepository.findById(idPersonne);
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
