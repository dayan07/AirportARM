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
import project.entity.*;
import project.service.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @Autowired
    PlaneService planeService;

    @Autowired
    ValidationDeleteService validationDeleteService;

    @RequestMapping("/")
    public ModelAndView planes(Model model){
        ModelAndView mav = new ModelAndView("index");
        if(((BindingAwareModelMap) model).size() !=0){
            String message = (String) model.asMap().get("msg");
            mav.addObject("messageAfterRemoved", message);
        }
        mav.addObject("planeList", planeService.showAllPlanes());
        return mav;
    }
    @RequestMapping(value = "/addPlane")
    public ModelAndView addPlane() {

        ModelAndView mav = new ModelAndView("addPlane");
        mav.addObject(new Plane());
        mav.addObject("isUpdateOrCreate", "Create plane");
        return mav;
    }
    @RequestMapping(value = "/createPlane", method = RequestMethod.POST)
    public String createPlane(@ModelAttribute Plane plane) {
        planeService.savePlane(plane);
        return "redirect:/";
    }

    @RequestMapping(value = "/updatePlane")
    public ModelAndView updatePlane(@RequestParam("id") String planeId) {
        Plane plane = planeService.getPlaneById(Integer.parseInt(planeId));
        ModelAndView mav = new ModelAndView("addPlane");
        mav.addObject(plane);
        mav.addObject("isUpdateOrCreate", "Update plane");
        return mav;
    }

    @RequestMapping(value = "/deletePlane")
    public String deletePlane(@RequestParam("id") String planeId, final RedirectAttributes redirectAttributes) {
        Integer result = validationDeleteService.removeItem(Integer.parseInt(planeId), "Plane");
        switch(result) {
            case (-1):
                redirectAttributes.addFlashAttribute("msg", "The plane cannot be removed, because it has any seats or flights!");
                break;
            case (0):
                redirectAttributes.addFlashAttribute("msg", "Sometimes went wrong. Please try again later.");
                break;
        }
        return "redirect:/";
    }
}
