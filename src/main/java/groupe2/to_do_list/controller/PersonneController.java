package groupe2.to_do_list.controller;

import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.repository.PersonneRepository;
import groupe2.to_do_list.repository.RoleRepository;
import groupe2.to_do_list.service.PersonneService;
import groupe2.to_do_list.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

@Controller
@RequestMapping(path="/personne")
public class PersonneController {
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
	private RoleRepository roleRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Personne> getAllPersonnes () {
        return personneRepository.findAll();
    }
    
    @GetMapping(path="/byId")
    public @ResponseBody String getPersonne (@RequestParam int id) {
        Optional<Personne> optionalPersonne = personneRepository.findById(id);
        if (optionalPersonne.isPresent()){
            Personne p = optionalPersonne.get();
            return p.toString();
        }else {
            return "Error";
        }
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (
    		@RequestParam String nom, 
    		@RequestParam String prenom, 
    		@RequestParam String password, 
    		@RequestParam int roleId) {
    	
    	Role r = roleRepository.findById(roleId).get();
        
        if (PersonneService.savePersonne(nom, prenom, password, r, personneRepository)) {
            return "Saved";
        }else return "Error";
    }
}
