package project.web.RESTController;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import project.entity.*;
import project.service.EmployeeService;
import project.service.SecurityService;
import project.service.UserService;
import project.service.UserServiceImpl;
import project.validator.UserValidator;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.UUID;



@RestController
public class UserRESTController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Response> registration(@RequestBody User user, BindingResult bindingResult) {

        Client client = user.getClientList().get(0);

        String decodePassword = user.getPassword();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = new Response("Validation failed", "400", "Validation failed",true, false, bindingResult.getAllErrors());
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
        Integer empId=userService.save(user, client);
        securityService.autologin(user.getUserName(), decodePassword);
        Response response = new Response(null, null, null,false, true, new UserResponse(user.getToken(),empId));
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<Response> login(@RequestParam("email") final String email,
                                              @RequestParam("password") final String password) {
            securityService.autologin(email, password);
            User user = userService.findByUserName(email);
            String userPost=user.getUserName()+" mobileAppUser";
            Integer empId = employeeService.getEmployeeByPost(userPost);
            Response response = new Response(null, null, null,false, true, new UserResponse(user.getToken(),empId));
            return new ResponseEntity<Response>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/get_clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getClients(@RequestParam("t") final String token) {

        List<Client> listOfClients = userService.showClientsByUserToken(token);

        if(listOfClients.isEmpty()){
            return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Client>>(listOfClients, HttpStatus.OK);

    }

}
