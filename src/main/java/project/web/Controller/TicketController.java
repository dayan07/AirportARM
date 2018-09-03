package project.web.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.entity.*;
import project.service.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Autowired
    ClientService clientService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    FlightService flightService;

    @Autowired
    SeatService seatService;


    @RequestMapping("/tickets")
    public ModelAndView tickets(){
        ModelAndView mav = new ModelAndView("tickets");
        mav.addObject("ticketList", ticketService.showAllTickets());
        return mav;

    }

    @RequestMapping(value = "/addTicket")
    public ModelAndView addTicket() {
        ModelAndView mav = new ModelAndView("addTicket");
        mav.addObject("clientList", clientService.showAllClients());
        mav.addObject("userList", userService.showAll());
        mav.addObject("employeeList", employeeService.showAllEmployees());
        mav.addObject("flightList", flightService.showAllFlights());
        mav.addObject("seatList", seatService.showAllSeats());
        mav.addObject(new Ticket());
        mav.addObject("isUpdateOrCreate", "Create ticket");
        return mav;
    }

    @RequestMapping(value = "/updateTicket")
    public ModelAndView updateTicket(@RequestParam("id") String ticketId) {
        Ticket ticket = ticketService.getTicketById(Integer.parseInt(ticketId));
        ModelAndView mav = new ModelAndView("addTicket");
        mav.addObject("clientList", clientService.showAllClients());
        mav.addObject("userList", userService.showAll());
        mav.addObject("employeeList", employeeService.showAllEmployees());
        mav.addObject("flightList", flightService.showAllFlights());
        mav.addObject("seatList", seatService.showAllSeats());
        mav.addObject("selectedClientId", ticket.client.getId());
        mav.addObject("selectedUserId", ticket.user.getId());
        mav.addObject("selectedEmployeeId", ticket.employee.getId());
        mav.addObject("selectedFlightId", ticket.flight.getId());
        mav.addObject("selectedSeatId", ticket.seat.getId());
        mav.addObject(ticket);
        mav.addObject("isUpdateOrCreate", "Update ticket");
        return mav;
    }

    @RequestMapping(value = "/createTicket", method = RequestMethod.POST)
    public String createTicket(@ModelAttribute Ticket ticket, HttpServletRequest request) {
        Client client = clientService.getClientById(Integer.parseInt(request.getParameter("clientId")));
        User user = userService.findById(Long.parseLong(request.getParameter("userId")));
        Employee employee = employeeService.getEmployeeById(Integer.parseInt(request.getParameter("employeeId")));
        Flight flight = flightService.getFlightById(Integer.parseInt(request.getParameter("flightId")));
        Seat seat = seatService.getSeatById(Integer.parseInt(request.getParameter("seatId")));
        ticket.setClient(client);
        ticket.setUser(user);
        ticket.setEmployee(employee);
        ticket.setFlight(flight);
        ticket.setSeat(seat);
        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }

    @RequestMapping(value = "/deleteTicket")
    public String deleteTicket(@RequestParam("id") String ticketId) {
        ticketService.removeTicket(Integer.parseInt(ticketId));
        return "redirect:/tickets";
    }
}
