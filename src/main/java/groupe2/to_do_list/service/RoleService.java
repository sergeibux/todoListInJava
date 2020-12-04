package groupe2.to_do_list.service;

import groupe2.to_do_list.entity.Personne;
import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.repository.PersonneRepository;
import groupe2.to_do_list.repository.RoleRepository;

import java.util.Optional;

public class RoleService{
	public static boolean saveRole(String nom, RoleRepository roleRepository) {
        try {
            Role r = new Role(nom);
            roleRepository.save(r);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean updateRole(Role role, RoleRepository roleRepository,
                                         String nom) {

        try {
            Optional<Role> roleToUpdate = roleRepository.findById(role.getIdRole());
            if (roleToUpdate.isPresent()) {
                Role roleUpdated = roleToUpdate.get();
                roleUpdated.setNom(nom);

                roleRepository.save(roleUpdated);

            }
        } catch (Exception e) {
            return false;
        }
        return true;

    }
}
