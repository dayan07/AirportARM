package project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Plane;
import project.entity.Route;

import java.util.List;

@Repository
public interface RouteRepository
        extends JpaRepository<Route, Integer> {
    List<Route> findByStartPointAndEndPoint(String startPoint, String endPoint);
}
