package project;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import project.entity.*;
import project.service.*;

import java.util.List;

@Configuration
@ComponentScan("project")
@EnableWebMvc
@Import(DBConfig.class)
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        FlightService flightService = context.getBean(FlightService.class);
        ClientService clientService = context.getBean(ClientService.class);
        EmployeeService employeeService = context.getBean(EmployeeService.class);
        PlaneService planeService = context.getBean(PlaneService.class);
        RouteService routeService = context.getBean(RouteService.class);
        SeatService seatService = context.getBean(SeatService.class);
        TicketService ticketService = context.getBean(TicketService.class);
//        UserDetailsServiceImpl userDetailsServiceImpl = context.getBean(UserDetailsServiceImpl.class);


        //BCryptPasswordEncoder encoder = context.getBean(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.class);


//        Plane plane = new Plane();
//        plane.setName("Boeing 737");
//        plane.setCreateDate("03/05/2010");
//        plane.setCountry("USA");
//        plane.setType("passenger");
//        plane.setManufacturer("Boeing Commercial Airplanes");
//        planeService.savePlane(plane);
//
//        Seat seat = new Seat();
//        seat.setLevelClass("business");
//        seat.setNumber(14);
//        seat.setRow(1);
//        seat.setPlane(plane);
//        seatService.saveSeat(seat);
//
//        Seat seat1 = new Seat();
//        seat1.setLevelClass("business");
//        seat1.setNumber(15);
//        seat1.setRow(1);
//        seat1.setPlane(plane);
//        seatService.saveSeat(seat1);
//
//        Route route = new Route();
//        route.setStartPoint("Minsk");
//        route.setEndPoint("Egypt");

//        routeService.saveRoute(route);
//
//        Flight flight = new Flight();
//        flight.setDate("03/05/2018");
//        flight.setStatus("ended success");
//        flight.setSaleSeatsCount(15);
//        flight.setPlane(plane);
//        flight.setRoute(route);
//        flightService.saveFlight(flight);
//
//        Client client = new Client();
//        client.setFirstName("Diana");
//        client.setLastName("Lobach");
//        clientService.saveClient(client);
//
//        Client client1 = new Client();
//        client1.setFirstName("Valery");
//        client1.setLastName("Zhuk");
//        clientService.saveClient(client1);
//
//        Employee employee = new Employee();
//        employee.setFirstName("Anna");
//        employee.setLastName("Hrabun");
//        employee.setPost("dispatcher");
//        employeeService.saveEmployee(employee);
//
//        Ticket ticket = new Ticket();
//        ticket.setCost("$180");
//        ticket.setClient(client);
//        ticket.setEmployee(employee);
//        ticket.setFlight(flight);
//        ticket.setSeat(seat);
//        ticketService.saveTicket(ticket);
//
//        Ticket ticket1 = new Ticket();
//        ticket1.setCost("$190");
//        ticket1.setClient(client1);
//        ticket1.setEmployee(employee);
//        ticket1.setFlight(flight);
//        ticket1.setSeat(seat1);
//        ticketService.saveTicket(ticket1);
//
//        flightService.showAllFlights();

    }
}