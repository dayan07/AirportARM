package project.web.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.entity.*;
import project.service.FlightService;
import project.service.TicketService;
import project.service.UserService;

import java.util.Arrays;
import java.util.List;


@RestController
public class TicketRESTController {


    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/my_tickets", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getMyTickets(@RequestParam("t") final String token) {
        List<Ticket> listOfTickets = userService.showTicketsByUserToken(token);

        if(listOfTickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ticket>>(listOfTickets, HttpStatus.OK);
    }

    @RequestMapping(value = "/save_tickets", method = RequestMethod.POST)
    public ResponseEntity<Response> saveTickets(@RequestBody Ticket[] ticketsArray, @RequestParam("t") final String token, @RequestParam("empId") final Integer empId, @RequestParam("fl") final Integer flightId) {
       List<Ticket> tickets = Arrays.asList(ticketsArray);
       tickets.forEach(ticket -> {
           ticketService.saveTicketFromREST(ticket, token, empId, flightId);

       });

        Response response = new Response(null, null, null,false, true, null);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }



}

