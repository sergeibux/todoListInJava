package groupe2.to_do_list.controller;

import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.repository.PersonneRepository;
import groupe2.to_do_list.repository.RoleRepository;
import groupe2.to_do_list.service.PersonneService;
import groupe2.to_do_list.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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

    @GetMapping("/connect")
    public String connect(Model model) {
        model.addAttribute("alert", "");
        return "connect";
    }
    
    @GetMapping("submit")
    public String submitConnection(@RequestParam(name="user", required=true, defaultValue="") String user,
    		@RequestParam(name="pwd", required=true, defaultValue="") String pwd,
    		Model model) {
    	if ((pwd == "") || (user == "")){
          model.addAttribute("alert", "Veuillez remplir tous les champs !");
          return "connect";
    	}
    	model.addAttribute("msg", "Bonjour " + user);
		return "connect";
    	
    }

    @GetMapping("/toto")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    // Show Register page.
    @RequestMapping(value = "/addpersonne", method = RequestMethod.GET)
    public String connexionpage(Model model) {

        Personne form = new Personne();
        model.addAttribute("title", "Ajouter une personne");
        model.addAttribute("appUserForm", form);

        return "addpersonne";
    }

    @RequestMapping(value = "/updatepersonne", method = RequestMethod.GET)
    public String updatepersonne(@RequestParam int id, Model model) {

        Optional<Personne> optionalPersonne = personneRepository.findById(id);
        if (optionalPersonne.isPresent()){
            Personne form = optionalPersonne.get();
            model.addAttribute("title", "Mettre a jours une personne");
            model.addAttribute("appUserForm", form);

            return "addpersonne";
        }else {
            return "Error";
        }
    }

    // This method is called to save the registration information.
    // @Validated: To ensure that this Form
    // has been Validated before this method is invoked.
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(Model model, //
                               @ModelAttribute("appUserForm") @Validated Personne appUserForm, //
                               BindingResult result, //
                               final RedirectAttributes redirectAttributes) {

        // Validate result
        if (result.hasErrors()) {
            return "addpersonne";
        }
        try {

            if (appUserForm.getId_Personne() == null){
                System.out.println("toto");
                PersonneService.savePersonne(appUserForm.getNom(), appUserForm.getPrenom(), appUserForm.getPassword(), null, personneRepository);
            } else {

                //update
            }
        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error");
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addpersonne";
        }
        return "redirect:/";
    }
}
