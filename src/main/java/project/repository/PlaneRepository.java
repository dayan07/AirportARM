package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Flight;
import project.entity.Plane;

@Repository
public interface PlaneRepository
        extends JpaRepository<Plane, Integer> {
}

