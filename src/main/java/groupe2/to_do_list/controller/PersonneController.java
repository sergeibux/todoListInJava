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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

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
        
        if (PersonneService.savePersonne(nom, prenom, password, r, personneRepository, roleRepository)) {
            return "Saved";
        }else return "Error";
    }

    @GetMapping("/connect")
    public String connect(
    		@RequestParam(name="err", required=false, defaultValue="") String err,
    		Model model) {
    	Personne p = new Personne();
    	
        model.addAttribute("err", err);
        model.addAttribute("appConnectForm", p);
        return "connect";
    }
    
    @RequestMapping(value = "/submitConnection", method = RequestMethod.POST)
    public String submitConnection(@ModelAttribute("appConnectForm") @Validated Personne appConnectForm,
            BindingResult result,
            final RedirectAttributes redirectAttributes,
            HttpServletResponse response,
            Model model) {
    	if (result.hasErrors()) {
    		String err = "Veuillez remplir tous les champs !";
    		return "redirect:/personne/connect?err=" + err;
        }
        try {
        	int id = PersonneService.getIdOnConnexion(appConnectForm.getNom(), appConnectForm.getPassword(), personneRepository);
        	if (id == -1){
        		String err = "Mauvais identifiant / mot de passe !";
        		return "redirect:/personne/connect?err=" + err;
            }
        	
        	Cookie cookieId = new Cookie("id", String.valueOf(id));
        	response.addCookie(cookieId);
        	
        	return "redirect:/tache/list";
        }
        // Other error!!
        catch (Exception e) {
        	String err = "Error: " + e.getMessage();
//        	model.addAttribute("errorMessage", "Error: " + e.getMessage());
        	return "redirect:/personne/connect?err=" + err;
        }
    	
    }

    @GetMapping("/toto")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    // Show Register page.
    @RequestMapping(value = "/addpersonne", method = RequestMethod.GET)
    public String addpersonne(Model model) {

        Personne form = new Personne();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("Roles", roles);
        model.addAttribute("title", "Ajouter une personne");
        model.addAttribute("appUserForm", form);

        return "addpersonne";
    }

    @RequestMapping(value = "/updatepersonne", method = RequestMethod.GET)
    public String updatepersonne(@RequestParam int id, Model model) {

        Optional<Personne> optionalPersonne = personneRepository.findById(id);
        if (optionalPersonne.isPresent()){
            Personne form = optionalPersonne.get();
            List<Role> roles = roleRepository.findAll();
            model.addAttribute("Roles", roles);
            model.addAttribute("title", "Mettre a jours une personne");
            model.addAttribute("appUserForm", form);

            return "addpersonne";
        }else {
            return "Error";
        }
    }


    @RequestMapping(value = "/deletepersonne", method = RequestMethod.GET)
    public String deletepersonne(@RequestParam int id, Model model) {
        PersonneService.deleteUser(id, personneRepository);
        return "deletepersonne";
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

            if (appUserForm.getIdPersonne() == null){
                PersonneService.savePersonne(appUserForm.getNom(), appUserForm.getPrenom(), appUserForm.getPassword(), appUserForm.getRole(), personneRepository, roleRepository);
            } else {
                //update
                PersonneService.updatePersonne(appUserForm, personneRepository, appUserForm.getNom(), appUserForm.getPrenom(), appUserForm.getPassword(), appUserForm.getRole());            }
        }
        // Other error!!
        catch (Exception e) {
            System.out.println("error");
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addpersonne";
        }
        return "redirect:/";
    }


    @RequestMapping(value = "/listpersonne", method = RequestMethod.GET)
    public String listpersonne(Model model) {
        List<Personne> personnes = personneRepository.findAll();
        model.addAttribute("userList", personnes);
        return "listpersonne";
    }

}
