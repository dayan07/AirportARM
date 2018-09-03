package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Flight;
import project.entity.Route;
import project.entity.Seat;
import project.repository.FlightRepository;
import project.repository.RouteRepository;
import project.repository.SeatRepository;

import java.util.List;

@Component
@Transactional
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public void saveSeat(Seat seat){

        seatRepository.save(seat);
    }

    public List<Seat> showAllSeats(){
        List <Seat> seatList = seatRepository.findAll();
        return seatList;
    }

    public Seat getSeatById(Integer id){
        return seatRepository.findById(id).get();
    }

    public Integer HasSeatTickets(Integer id) {
        Seat seatToDelete = getSeatById(id);
        if(seatToDelete.ticketList.size() == 0 ){
            return 1;
        }
        else {
            return -1;
        }
    }


}
