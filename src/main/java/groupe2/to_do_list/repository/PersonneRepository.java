package groupe2.to_do_list.repository;

import groupe2.to_do_list.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer>{
	Personne findByNomAndPasswordLike(String nom, String password);
}
