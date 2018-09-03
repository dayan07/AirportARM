package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.entity.*;
import project.repository.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Component
@Transactional
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    FlightService flightService;

    @Autowired
    SeatService seatService;

    @Autowired
    ValidationDeleteService validationDeleteService;


    public void saveTicket(Ticket ticket){

        ticketRepository.save(ticket);
    }
    public void saveTicketFromREST(Ticket ticket, String token, Integer empId, Integer flightId){
        User user = userRepository.findByToken(token);
        Employee employee = employeeService.getEmployeeById(empId);
        Flight flight = flightService.getFlightById(flightId);
        Seat seat = seatService.getSeatById(ticket.seat.getId());
        String clientPassportNumber = ticket.client.getPassportNumber();
        Client client = clientRepository.findByPassportNumber(clientPassportNumber);
        if (client ==null){
            ticket.client.setUser(user);
            clientService.saveClient(ticket.client);
            Client addedClient = clientRepository.findByPassportNumber(clientPassportNumber);
            ticket.setClient(addedClient);
        }
        else{
            ticket.setClient(client);
        }
        ticket.setUser(user);
        ticket.setEmployee(employee);
        int availableSeatsCount = flight.getAvailableSeatsCount();
        flight.setAvailableSeatsCount(--availableSeatsCount);
        seat.setAvailable(false);
        ticket.setFlight(flight);
        ticket.setSeat(seat);
        ticketRepository.save(ticket);
    }

    public List<Ticket> showAllTickets(){
        List <Ticket> ticketList = ticketRepository.findAll();
        return ticketList;
    }

    public Ticket getTicketById(Integer id){
        return ticketRepository.findById(id).get();
    }

    public Ticket getTicketByCost(String cost){
        return ticketRepository.findByCost(cost);
    }

    public void removeTicket(Integer id){
        ticketRepository.deleteById(id);
    }



}
