package Groupe2.To_do_list.Repository;

import Groupe2.To_do_list.Entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer>{
}
