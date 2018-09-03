package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String levelClass;
    int row;
    String number;
    boolean available = true;

    @OneToMany(mappedBy="seat")
    @JsonIgnore
    public List<Ticket> ticketList;

    @ManyToOne
    @JoinColumn(name="PLANE_ID", nullable = false)
    @JsonIgnore
    public Plane plane;

    public boolean getAvailable(){
        return available;


    }
}
