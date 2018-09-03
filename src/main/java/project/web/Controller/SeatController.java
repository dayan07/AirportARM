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
import project.entity.Plane;
import project.entity.Seat;
import project.service.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SeatController {
    @Autowired
    PlaneService planeService;

    @Autowired
    SeatService seatService;

    @Autowired
    ValidationDeleteService validationDeleteService;

    @RequestMapping("/seats")
    public ModelAndView seats(Model model){
        ModelAndView mav = new ModelAndView("seats");
        if(((BindingAwareModelMap) model).size() !=0){
            String message = (String) model.asMap().get("msg");
            mav.addObject("messageAfterRemoved", message);
        }
        mav.addObject("seatList", seatService.showAllSeats());
        return mav;
    }

    @RequestMapping(value = "/addSeat")
    public ModelAndView addSeat() {
        String isUpdateOrCreate = "Create seat";
        ModelAndView mav = new ModelAndView("addSeat");
        mav.addObject("planeList", planeService.showAllPlanes());
        mav.addObject("isUpdateOrCreate", isUpdateOrCreate);
        mav.addObject(new Seat());
        return mav;
    }

    @RequestMapping(value = "/createSeat", method = RequestMethod.POST)
    public String createSeat(@ModelAttribute Seat seat, HttpServletRequest request) {
        Plane plane = planeService.getPlaneById(Integer.parseInt(request.getParameter("planeId")));
        seat.setPlane(plane);
        seatService.saveSeat(seat);
        return "redirect:/seats";
    }

    @RequestMapping(value = "/updateSeat")
    public ModelAndView updateSeat(@RequestParam("id") String seatId) {
        Seat seat = seatService.getSeatById(Integer.parseInt(seatId));
        String isUpdateOrCreate = "Update seat";
        ModelAndView mav = new ModelAndView("addSeat");
        mav.addObject("planeList", planeService.showAllPlanes());
        mav.addObject("selectedPlaneId", seat.plane.getId());
        mav.addObject("isUpdateOrCreate", isUpdateOrCreate);
        mav.addObject(seat);
        return mav;
    }

    @RequestMapping(value = "/deleteSeat")
    public String deleteSeat(@RequestParam("id") String seatId, final RedirectAttributes redirectAttributes) {
        Integer result = validationDeleteService.removeItem(Integer.parseInt(seatId), "Seat");
        switch(result) {
            case (-1):
                redirectAttributes.addFlashAttribute("msg", "The seat cannot be removed, because it has any tickets!");
                break;
            case (0):
                redirectAttributes.addFlashAttribute("msg", "Sometimes went wrong. Please try again later.");
                break;
        }
        return "redirect:/seats";
    }



}
