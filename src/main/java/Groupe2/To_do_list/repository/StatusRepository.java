package Groupe2.To_do_list.repository;

import Groupe2.To_do_list.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{
}
