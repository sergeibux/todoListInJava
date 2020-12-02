package Groupe2.To_do_list.Controller;

import Groupe2.To_do_list.Entity.Personne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

import javax.persistence.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class PersonneController {
    @Autowired
    private Groupe2.To_do_list.Repository.PersonneRepository personneRepository;

//    @GetMapping(path="/personne") // Map ONLY GET Requests
//    public @ResponseBody String getPersonne (@RequestParam int id) {
//        Personne p = new Personne();
//        return p.to_string();
//    }
//    
//    @PostMapping(path="/add_Personne") // Map ONLY GET Requests
//    public @ResponseBody String addNewUser (@RequestParam String nom, @RequestParam String prenom, Role role) {
//        Client n = new Client();
//        if (n.saveClient(nom, prenom, ClientRepository)) {
//            return "Saved";
//        }else return "Error";
//    }
}
