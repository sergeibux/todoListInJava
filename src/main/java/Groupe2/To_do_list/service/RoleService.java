package Groupe2.To_do_list.Service;

import Groupe2.To_do_list.Repository.RoleRepository;
import Groupe2.To_do_list.Entity.Role;

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
