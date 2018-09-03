package project.web.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.entity.Flight;
import project.entity.Response;
import project.entity.Ticket;
import project.entity.UserResponse;
import project.service.FlightService;
import org.springframework.web.bind.annotation.*;
import project.service.TicketService;

import java.util.ArrayList;
import java.util.List;


@RestController
public class FlightRESTController {

    @Autowired
    FlightService flightService;

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/all_flights", method = RequestMethod.GET)
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> listOfFlights = flightService.showAllFlights();
        if(listOfFlights.isEmpty()){
            return new ResponseEntity<List<Flight>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Flight>>(listOfFlights, HttpStatus.OK);
    }

    @RequestMapping(value = "/all_flights_search", method = RequestMethod.GET)
    public ResponseEntity<Response> getFlightsSearch(@RequestParam("sp") final String startPoint,
                                                         @RequestParam("ep") final String endPoint,
                                                         @RequestParam("sd") final String startDate,
                                                         @RequestParam("ed") final String endDate) {
        List<Flight> listOfFlights = flightService.showFlightsByPointsAndDate(startPoint, endPoint, startDate, endDate);
        if(listOfFlights.isEmpty()){
            Response response = new Response(null, null, null,false, true, null);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
        Response response = new Response(null, null, null,false, true, listOfFlights);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }


}

