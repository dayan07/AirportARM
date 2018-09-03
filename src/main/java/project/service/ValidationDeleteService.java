package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.repository.*;


@Component
@Transactional
public class ValidationDeleteService {

    @Autowired
    PlaneService planeService;
    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    FlightService flightService;
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    RouteService routeService;
    @Autowired
    RouteRepository routeRepository;

    @Autowired
    SeatService seatService;
    @Autowired
    SeatRepository seatRepository;


    public Integer removeItem(Integer id, String item){
        JpaRepository itemRepository = null;
        Integer result = 0;
        switch (item) {
            case "Client":
                result = clientService.HasClientTickets(id);
                itemRepository = clientRepository;
                break;
            case "Employee":
                result = employeeService.HasEmployeeTickets(id);
                itemRepository = employeeRepository;
                break;
            case "Flight":
                result = flightService.HasFlightTickets(id);
                itemRepository = flightRepository;
                break;
            case "Plane":
                result = planeService.HasPlaneSeatsOrFlights(id);
                itemRepository = planeRepository;
                break;
            case "Route":
                result = routeService.HasRouteFlights(id);
                itemRepository = routeRepository;
                break;
            case "Seat":
                result = seatService.HasSeatTickets(id);
                itemRepository = seatRepository;
                break;
        }
        try {
            if(result == 1){
                itemRepository.deleteById(id);
                return 1;
            }
            else if(result == -1){
                return -1;
            }
        }
        catch (Exception e ){
            System.out.println(e.toString());
            return 0;
        }
        return 0;
    }




}
