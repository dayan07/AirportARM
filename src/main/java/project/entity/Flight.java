package project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String date;

    String status;
    int availableSeatsCount;

    @OneToMany(mappedBy="flight")
    @JsonIgnore
    public List<Ticket> ticketList;


    @ManyToOne
    @JoinColumn(name="PLANE_ID", nullable = false)
    public Plane plane;

    @ManyToOne
    @JoinColumn(name="ROUTE_ID", nullable = false)
    public Route route;

}
