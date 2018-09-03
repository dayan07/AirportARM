package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Route;
import project.entity.Seat;

@Repository
public interface SeatRepository
        extends JpaRepository<Seat, Integer> {
}

