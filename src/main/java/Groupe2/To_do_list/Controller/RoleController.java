package Groupe2.To_do_list.Controller;

import Groupe2.To_do_list.Entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

import javax.persistence.*;

@Controller
@RequestMapping(path="/role")
public class RoleController {
    @Autowired
    private Groupe2.To_do_list.Repository.RoleRepository roleRepository;

    @GetMapping(path="/byId")
    public @ResponseBody String getRole (@RequestParam int id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            Role r = optionalRole.get();
            return r.to_string();
        }else {
            return "Error";
        }

    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String nom) {
    	Role r = new Role();
        if (r.saveRole(nom, roleRepository)) {
            return "Saved";
        }else return "Error";
    }
}
