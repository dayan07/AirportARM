package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Route;
import project.entity.Ticket;

@Repository
public interface TicketRepository
        extends JpaRepository<Ticket, Integer> {

    Ticket findByCost(String cost);


}

