package groupe2.to_do_list.controller;

import groupe2.to_do_list.entity.Status;
import groupe2.to_do_list.repository.StatusRepository;
import groupe2.to_do_list.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/status")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Status> getAllSatus () {
        return statusRepository.findAll();
    }

    @GetMapping(path="/byId")
    public @ResponseBody String getStatus (@RequestParam int id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isPresent()){
            Status s = optionalStatus.get();
            return s.toString();
        }else {
            return "Error";
        }
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewStatus (@RequestParam String nom) {
        if (StatusService.saveStatus(nom, statusRepository)) {
            return "Saved";
        }else return "Error";
    }

}
