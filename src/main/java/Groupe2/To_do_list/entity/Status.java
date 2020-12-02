package groupe2.to_do_list.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_Status;

    private String nom;
}
