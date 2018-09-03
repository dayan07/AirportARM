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
import project.service.ClientService;
import project.service.UserService;
import project.service.UserServiceImpl;
import project.service.ValidationDeleteService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    UserService userService;

    @Autowired
    ValidationDeleteService validationDeleteService;

    @RequestMapping("/clients")
    public ModelAndView clients(Model model){
        ModelAndView mav = new ModelAndView("clients");
        if(((BindingAwareModelMap) model).size() !=0){
            String message = (String) model.asMap().get("msg");
            mav.addObject("messageAfterRemoved", message);
        }
        mav.addObject("clientList", clientService.showAllClients());
        return mav;
    }

    @RequestMapping(value = "/addClient")
    public ModelAndView addClient() {
        ModelAndView mav = new ModelAndView("addClient");
        mav.addObject("userList", userService.showAll());
        mav.addObject(new Client());
        mav.addObject("isUpdateOrCreate", "Create client");
        return mav;
    }

    @RequestMapping(value = "/updateClient")
    public ModelAndView updateClient(@RequestParam("id") String clientId) {
        Client client = clientService.getClientById(Integer.parseInt(clientId));
        ModelAndView mav = new ModelAndView("addClient");
        mav.addObject("userList", userService.showAll());
        mav.addObject("selectedUserId", client.user.getId());
        mav.addObject(client);
        mav.addObject("isUpdateOrCreate", "Update client");
        return mav;
    }

    @RequestMapping(value = "/deleteClient")
    public String deleteClient(@RequestParam("id") String clientId, final RedirectAttributes redirectAttributes) {
        Integer result = validationDeleteService.removeItem(Integer.parseInt(clientId), "Client");
        switch(result) {
            case (-1):
                redirectAttributes.addFlashAttribute("msg", "The client cannot be removed, because he has any tickets!");
                break;
            case (0):
                redirectAttributes.addFlashAttribute("msg", "Sometimes went wrong. Please try again later.");
                break;
        }
        return "redirect:/clients";
    }

    @RequestMapping(value = "/createClient", method = RequestMethod.POST)
    public String createClient(@ModelAttribute Client client, HttpServletRequest request) {
        User user = userService.findById(Long.parseLong(request.getParameter("userId")));
        client.setUser(user);
        clientService.saveClient(client);
        return "redirect:/clients";
    }

}
