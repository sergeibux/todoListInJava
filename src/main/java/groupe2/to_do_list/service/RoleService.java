package groupe2.to_do_list.service;

import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.repository.RoleRepository;

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
}
