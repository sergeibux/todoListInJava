package Groupe2.To_do_list.Repository;

import Groupe2.To_do_list.Entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer>{
}
