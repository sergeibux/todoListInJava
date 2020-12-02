package groupe2.to_do_list.service;

import groupe2.to_do_list.entity.Status;
import groupe2.to_do_list.repository.StatusRepository;

public class StatusService {
    public static boolean saveStatus(String nom, StatusRepository statusRepository) {
        try {
            Status s = new Status(nom);
            statusRepository.save(s);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
