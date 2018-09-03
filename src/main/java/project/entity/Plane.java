package project.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String manufacturer;
    String type;
    String country;
    String createDate;
    String name;


    @OneToMany(mappedBy="plane")
    public List<Seat> seatList;

    @OneToMany(mappedBy="plane")
    @JsonIgnore
    public List<Flight> flightList;






}
