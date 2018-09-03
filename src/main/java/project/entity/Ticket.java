package project.entity;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String cost;


    @ManyToOne
    @JoinColumn(name="CLIENT_ID")
    public Client client;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    @JsonIgnore
    public User user;


    @ManyToOne
    @JoinColumn(name="EMPLOYEE_ID")
    public Employee employee;


    @ManyToOne
    @JoinColumn(name="SEAT_ID", nullable = false)
    public Seat seat;


    @ManyToOne
    @JoinColumn(name="FLIGHT_ID", nullable = false)
    public Flight flight;
}
