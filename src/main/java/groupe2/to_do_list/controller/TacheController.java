package groupe2.to_do_list.controller;

import groupe2.to_do_list.entity.Tache;
import groupe2.to_do_list.entity.Role;
import groupe2.to_do_list.entity.Personne;
import groupe2.to_do_list.entity.Status;
import groupe2.to_do_list.repository.TacheRepository;
import groupe2.to_do_list.repository.PersonneRepository;
import groupe2.to_do_list.repository.StatusRepository;
import groupe2.to_do_list.service.TacheService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	        		date,
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
}
