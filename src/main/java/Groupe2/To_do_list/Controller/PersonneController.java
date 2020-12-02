package Groupe2.To_do_list.Controller;

import Groupe2.To_do_list.Entity.Personne;
import Groupe2.To_do_list.Service.PersonneService;
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
@RequestMapping(path="/personne")
public class PersonneController {
    @Autowired
    private Groupe2.To_do_list.Repository.PersonneRepository personneRepository;
    @Autowired
	private Groupe2.To_do_list.Repository.RoleRepository roleRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Personne> getAllPersonnes () {
        return personneRepository.findAll();
    }
    
    @GetMapping(path="/byId")
    public @ResponseBody String getPersonne (@RequestParam int id) {
        Optional<Personne> optionalPersonne = personneRepository.findById(id);
        if (optionalPersonne.isPresent()){
            Personne p = optionalPersonne.get();
            return p.to_string();
        }else {
            return "Error";
        }
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (
    		@RequestParam String nom, 
    		@RequestParam String prenom, 
    		@RequestParam int roleId) {
    	
    	Role r = roleRepository.findById(roleId).get();
        
    	Personne p = new Personne();
        if (PersonneService.savePersonne(nom, prenom, r, personneRepository)) {
            return "Saved";
        }else return "Error";
    }
}
