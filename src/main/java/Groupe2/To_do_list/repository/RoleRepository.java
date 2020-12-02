package groupe2.to_do_list.repository;

<<<<<<< HEAD:src/main/java/Groupe2/To_do_list/Repository/RoleRepository.java
import Groupe2.To_do_list.Entity.Role;
import org.springframework.data.repository.CrudRepository;
=======
import groupe2.to_do_list.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
>>>>>>> 7011313cff33e07531ff2e6f3d4a52826a900b1d:src/main/java/Groupe2/To_do_list/repository/RoleRepository.java


public interface RoleRepository extends CrudRepository<Role, Integer> {

}
