package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Employee;
import project.entity.Flight;

import java.util.List;


@Repository
public interface FlightRepository
        extends JpaRepository<Flight, Integer> {
    List<Flight> findByDateBetween(String startDate, String endDate);
}
