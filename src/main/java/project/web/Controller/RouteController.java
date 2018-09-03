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
import project.entity.Route;
import project.service.RouteService;
import project.service.ValidationDeleteService;

@Controller
public class RouteController {

    @Autowired
    RouteService routeService;

    @Autowired
    ValidationDeleteService validationDeleteService;

    @RequestMapping("/routes")
    public ModelAndView routes(Model model){
        ModelAndView mav = new ModelAndView("routes");
        if(((BindingAwareModelMap) model).size() !=0){
            String message = (String) model.asMap().get("msg");
            mav.addObject("messageAfterRemoved", message);
        }
        mav.addObject("routeList", routeService.showAllRoutes());
        return mav;

    }

    @RequestMapping(value = "/addRoute")
    public ModelAndView addRoute() {
        ModelAndView mav = new ModelAndView("addRoute");
        mav.addObject(new Route());
        mav.addObject("isUpdateOrCreate", "Create route");
        return mav;
    }

    @RequestMapping(value = "/updateRoute")
    public ModelAndView updateRoute(@RequestParam("id") String routeId) {
        Route route = routeService.getRouteById(Integer.parseInt(routeId));
        ModelAndView mav = new ModelAndView("addRoute");
        mav.addObject(route);
        mav.addObject("isUpdateOrCreate", "Update route");
        return mav;
    }

    @RequestMapping(value = "/createRoute", method = RequestMethod.POST)
    public String createEmployee(@ModelAttribute Route route) {
        routeService.saveRoute(route);
        return "redirect:/routes";
    }

    @RequestMapping(value = "/deleteRoute")
    public String deleteRoute(@RequestParam("id") String routeId, final RedirectAttributes redirectAttributes) {
        Integer result = validationDeleteService.removeItem(Integer.parseInt(routeId), "Route");
        switch(result) {
            case (-1):
                redirectAttributes.addFlashAttribute("msg", "The route cannot be removed, because it has any flights!");
                break;
            case (0):
                redirectAttributes.addFlashAttribute("msg", "Sometimes went wrong. Please try again later.");
                break;
        }
        return "redirect:/routes";
    }
}
