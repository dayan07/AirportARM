package project.web.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.entity.Flight;
import project.entity.Plane;
import project.entity.Route;
import project.service.FlightService;
import project.service.PlaneService;
import project.service.RouteService;
import project.service.ValidationDeleteService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    ValidationDeleteService validationDeleteService;

    @Autowired
    PlaneService planeService;

    @Autowired
    RouteService routeService;


    @RequestMapping("/flights")
    public ModelAndView flights(Model model){
        ModelAndView mav = new ModelAndView("flights");
        if(((BindingAwareModelMap) model).size() !=0){
            String message = (String) model.asMap().get("msg");
            mav.addObject("messageAfterRemoved", message);
        }
        mav.addObject("flightList", flightService.showAllFlights());
        return mav;

    }

    @RequestMapping(value = "/addFlight")
    public ModelAndView addFlight() {
        ModelAndView mav = new ModelAndView("addFlight");
        mav.addObject("planeList", planeService.showAllPlanes());
        mav.addObject("routeList", routeService.showAllRoutes());
        mav.addObject(new Flight());
        mav.addObject("isUpdateOrCreate", "Create flight");
        return mav;
    }

    @RequestMapping(value = "/updateFlight")
    public ModelAndView updateFlight(@RequestParam("id") String flightId) {
        ModelAndView mav = new ModelAndView("addFlight");
        mav.addObject("planeList", planeService.showAllPlanes());
        mav.addObject("routeList", routeService.showAllRoutes());
        Flight flight = flightService.getFlightById(Integer.parseInt(flightId));
        mav.addObject("selectedPlaneId", flight.plane.getId());
        mav.addObject("selectedRouteId", flight.route.getId());
        mav.addObject(flight);
        mav.addObject("isUpdateOrCreate", "Update flight");
        return mav;
    }

    @RequestMapping(value = "/deleteFlight")
    public String deleteFlight(@RequestParam("id") String flightId, final RedirectAttributes redirectAttributes) {
        Integer result = validationDeleteService.removeItem(Integer.parseInt(flightId), "Flight");
        switch(result) {
            case (-1):
                redirectAttributes.addFlashAttribute("msg", "The flight cannot be removed, because it has any tickets!");
                break;
            case (0):
                redirectAttributes.addFlashAttribute("msg", "Sometimes went wrong. Please try again later.");
                break;
        }
        return "redirect:/flights";
    }

    @RequestMapping(value = "/createFlight", method = RequestMethod.POST)
    public String createFlight(@ModelAttribute Flight flight, HttpServletRequest request) {
        Plane plane = planeService.getPlaneById(Integer.parseInt(request.getParameter("planeId")));
        Route route = routeService.getRouteById(Integer.parseInt(request.getParameter("routeId")));
        flight.setPlane(plane);
        flight.setRoute(route);
        flightService.saveFlight(flight);
        return "redirect:/flights";
    }
}