package project.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String firstName;
    String lastName;
    String post;

    @JsonIgnore
    @OneToMany(mappedBy="employee")
    public List<Ticket> ticketList;


   }
