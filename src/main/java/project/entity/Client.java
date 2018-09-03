package project.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String firstName;
    String lastName;
    String dateOfBirth;
    String passportNumber;
    String dateOfIssue;
    String expiryDate;
    String email;
    String phoneNumber;

    @OneToMany(mappedBy="client")
    @JsonIgnore
    public List<Ticket> ticketList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="USER_ID")
    public User user;

}
