package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Flight;
import project.entity.Route;
import project.repository.FlightRepository;
import project.repository.RouteRepository;

import java.util.List;

@Component
@Transactional
public class RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    FlightRepository flightRepository;

    public void saveRoute(Route route){
        routeRepository.save(route);
    }

    public List<Route> showAllRoutes(){
        List <Route> schoolBoyList = routeRepository.findAll();
        return schoolBoyList;
    }


    public Route getRouteById(Integer id){
        return routeRepository.findById(id).get();
    }

    public Integer HasRouteFlights(Integer id) {

        Route routeToDelete = getRouteById(id);
        if(routeToDelete.flightList.size() == 0 ){
            return 1;
        }
        else {
            return -1;
        }

    }


}

