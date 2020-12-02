package Groupe2.To_do_list.Controller;

import Groupe2.To_do_list.Entity.Role;
import Groupe2.To_do_list.Repository.RoleRepository;
import Groupe2.To_do_list.Service.RoleService;
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
    private RoleRepository roleRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Role> getAllRoles () {
        return roleRepository.findAll();
    }

    @GetMapping(path="/byId")
    public @ResponseBody String getRole (@RequestParam int id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            Role r = optionalRole.get();
            return r.toString();
        }else {
            return "Error";
        }
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String nom) {
        if (RoleService.saveRole(nom, roleRepository)) {
            return "Saved";
        }else return "Error";
    }
}
