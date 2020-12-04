package groupe2.to_do_list.controller;

import groupe2.to_do_list.entity.Tache;
import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.entity.Personne;
import groupe2.to_do_list.entity.Status;
import groupe2.to_do_list.repository.TacheRepository;
import groupe2.to_do_list.repository.PersonneRepository;
import groupe2.to_do_list.repository.StatusRepository;
import groupe2.to_do_list.service.PersonneService;
import groupe2.to_do_list.service.TacheService;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;





@Controller
@RequestMapping(path="/tache")
public class TacheController {
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private StatusRepository statusRepository;


    @GetMapping(path="/all")
    public @ResponseBody List<Tache> getAllTaches () {
        return (List<Tache>) tacheRepository.findAll();
    }

    @GetMapping(path="/byId")
    public @ResponseBody String getTache (@RequestParam int id) {
        Optional<Tache> optionalTache = tacheRepository.findById(id);
        if (optionalTache.isPresent()){
            Tache t = optionalTache.get();
            return t.toString();
        }else {
            return "Error";
        }
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewTask (@RequestParam String titre,
    		@RequestParam String texte,
			@RequestParam String dateCreation,
			@RequestParam int personneId,
			@RequestParam int personneCreationId,
			@RequestParam int statusId) {

    	try {
	    	Optional<Personne> optionalPersonne = personneRepository.findById(personneId);
	    	if (!optionalPersonne.isPresent())
	    		return "error #1 : no Personne field with id " + personneId;
	    	Personne personne = optionalPersonne.get();
	    	
	    	Optional<Personne> optionalPersonneCreation = personneRepository.findById(personneCreationId);
	    	if (!optionalPersonneCreation.isPresent())
	    		return "error #2 : no Personne field with id " + personneCreationId;
	    	Personne personne_creation = optionalPersonneCreation.get();
	    	
	    	Optional<Status> optionalStatus = statusRepository.findById(statusId);
	    	if (!optionalStatus.isPresent())
	    		return "error #3 : no Status field with id " + statusId;    	
	    	Status status = optionalStatus.get();
	    	
	    	Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateCreation);
	    	
	        if (TacheService.saveTache(
	        		titre,
	        		texte,
	        		personne,
	        		personne_creation,
	        		status,
	        		tacheRepository)) {
	            return "Saved";
	        }else return "Error";
    	} catch (Exception e) {
	    	return "Unhandled error : " + e;
	    }
    }

	// Show Register page.
	@RequestMapping(value = "/addtache", method = RequestMethod.GET)
	public String addpersonne(Model model) {

		Tache tache = new Tache();
		List<Status> statuses = statusRepository.findAll();
		List<Personne> personnes = personneRepository.findAll();
		model.addAttribute("Statuses", statuses);
		model.addAttribute("Personnes", personnes);
		model.addAttribute("title", "Ajouter une tache");
		model.addAttribute("tache", tache);

		return "addtache";
	}

	@RequestMapping(value = "/updatetache", method = RequestMethod.GET)
	public String updatepersonne(@RequestParam int id, Model model) {

		Optional<Tache> optionalTache = tacheRepository.findById(id);
		if (optionalTache.isPresent()){
			Tache tache = optionalTache.get();
			List<Status> status = statusRepository.findAll();
			List<Personne> personnes = personneRepository.findAll();
			model.addAttribute("Statuses", status);
			model.addAttribute("Personnes", personnes);
			model.addAttribute("title", "Ajouter une tache");
			model.addAttribute("tache", tache);

			return "addtache";

		}else {
			return "Error";
		}
	}

	// This method is called to save the registration information.
	// @Validated: To ensure that this Form
	// has been Validated before this method is invoked.
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveRegister(Model model, //
							   @ModelAttribute("tache") @Validated Tache tache, //
							   BindingResult result, //
							   final RedirectAttributes redirectAttributes) {

		// Validate result
		if (result.hasErrors()) {
			return "addtache";
		}
		try {
			if (tache.getId_Tache() == null){
				TacheService.saveTache(tache.getTitre(), tache.getTexte(), tache.getPersonne(), null, tache.getStatus(), tacheRepository);
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
    
    @GetMapping("/list")
    public String list(
    		@CookieValue(value="id", defaultValue="") String id,
    		Model model) {
 
    	Status pendingStatus = statusRepository.findByNomLike("%en cours%");
    	List<Tache> todoTasks = (List<Tache>) tacheRepository.findByStatus_IdStatus(pendingStatus.getIdStatus());
    	
    	Status doneStatus = statusRepository.findByNomLike("%termin%");
    	List<Tache> doneTasks = (List<Tache>) tacheRepository.findByStatus_IdStatus(doneStatus.getIdStatus());
    	
    	model.addAttribute("todoTasks", todoTasks);
    	model.addAttribute("doneTasks", doneTasks);
    	
    	Optional <Personne> optionalPersonne = personneRepository.findById(Integer.parseInt(id));
    	if (optionalPersonne.isPresent())
    		model.addAttribute("msg", optionalPersonne.get().getPrenom());
        return "list";
    }    
}
