package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Flight;
import project.entity.Plane;
import project.entity.Seat;
import project.repository.FlightRepository;
import project.repository.PlaneRepository;

import javax.persistence.OneToMany;
import java.util.List;

@Component
@Transactional
public class PlaneService {

    @Autowired
    PlaneRepository planeRepository;

    public void savePlane(Plane plane){
        planeRepository.save(plane);
    }
    public Plane getPlaneById(Integer id){
        return planeRepository.findById(id).get();
    }

    public Integer HasPlaneSeatsOrFlights(Integer id){
        Plane planeToDelete = getPlaneById(id);
        if(planeToDelete.seatList.size() == 0 && planeToDelete.flightList.size() == 0 ){
            return 1;
        }
        else {
            return -1;
        }
    }

    public List<Plane> showAllPlanes(){
        List <Plane> planeList = planeRepository.findAll();
        return planeList;
    }
}
