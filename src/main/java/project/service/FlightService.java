package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.entity.*;
import project.repository.FlightRepository;
import project.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    RouteRepository routeRepository;

    public void saveFlight(Flight flight){
        flightRepository.save(flight);
    }

    public void createFlight(String flightDate){
        Flight flight = new Flight();
        flight.setDate(flightDate);
        flightRepository.save(flight);
    }
    public List<Flight> showAllFlights(){
        List <Flight> flightList = flightRepository.findAll();
        List <Flight> flightListRes=new ArrayList<>();
        flightList.forEach(flight->{
            Plane plane = flight.getPlane();
            List <Seat> seats = plane.getSeatList();
            List <Seat> seatsRes = new ArrayList<>();
            for (Seat seat : seats) {
                if(seat.getAvailable()){
                    seatsRes.add(seat);
                }
            }
            plane.setSeatList(seatsRes);
            flight.setPlane(plane);
            flightListRes.add(flight);

        });
        return flightListRes;
    }

    public List<Flight> showFlightsByPointsAndDate(String startPoint, String endPoint, String startDate, String endDate){
        List <Flight> resFlightList = new ArrayList<Flight>() ;
        List <Flight> flightList = flightRepository.findByDateBetween(startDate, endDate);
        List <Route> routeList = routeRepository.findByStartPointAndEndPoint(startPoint, endPoint);
        for (int i = 0; i < flightList.size(); i++) {
            for (int j = 0; j < routeList.size(); j++) {
                if (flightList.get(i).route.equals(routeList.get(j))){
                    resFlightList.add(flightList.get(i));
                }
            }
        }
        List <Flight> flightListRes=new ArrayList<>();
        resFlightList.forEach(flight->{
            Plane plane = flight.getPlane();
            List <Seat> seats = plane.getSeatList();
            List <Seat> seatsRes = new ArrayList<>();
            for (Seat seat : seats) {
                if(seat.getAvailable()){
                    seatsRes.add(seat);
                }
            }
            plane.setSeatList(seatsRes);
            flight.setPlane(plane);
            flightListRes.add(flight);

        });
        return flightListRes;
    }

    public Flight getFlightById(Integer id){
        return flightRepository.findById(id).get();
    }

//    public Route getRouteByFlightId(Integer id){
//        Flight flight = getFlightById(id);
//        return flight.getRoute();
//    }

    public Integer HasFlightTickets(Integer id) {
        Flight flightToDelete = getFlightById(id);
        if(flightToDelete.ticketList.size() == 0 ){
            return 1;
        }
        else {
            return -1;
        }
    }
}
