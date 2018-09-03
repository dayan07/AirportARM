package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String startPoint;
    String endPoint;

    @OneToMany(mappedBy="route")
    @JsonIgnore
    public List<Flight> flightList;


//    @OneToMany(mappedBy = "trainee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Column(nullable = true)
//    @JsonManagedReference
//    private Set<BodyStat> bodyStats;


}
