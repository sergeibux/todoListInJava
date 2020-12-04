package groupe2.to_do_list.repository;

import groupe2.to_do_list.entity.Tache;
import groupe2.to_do_list.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer>{
	List<Tache> findByStatus_IdStatus(int id);
}
