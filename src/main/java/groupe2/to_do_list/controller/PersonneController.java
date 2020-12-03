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
    public String connect(@RequestParam(name="msg", required=false, defaultValue="") String msg,
    		@RequestParam(name="err", required=false, defaultValue="") String err,
    		Model model) {
    	Personne form = new Personne();
        model.addAttribute("msg", msg);
        model.addAttribute("err", err);
        model.addAttribute("appConnectForm", form);
        return "connect";
    }
    
    @RequestMapping(value = "/submitConnection", method = RequestMethod.POST)
    public String submitConnection(@ModelAttribute("appConnectForm") @Validated Personne appConnectForm,
            BindingResult result,
            final RedirectAttributes redirectAttributes,
            Model model) {
    	if (result.hasErrors()) {
    		String err = "Veuillez vérifier vos données !";
    		return "redirect:/personne/connect?err=" + err;
        }
        try {
        	int id = PersonneService.getIdOnConnexion(appConnectForm.getNom(), appConnectForm.getPassword(), personneRepository);
//        	String prenom = appConnectForm.getNom();
        	return "redirect:/personne/connect?msg=" + id;
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
    public String connexionpage(Model model) {

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
