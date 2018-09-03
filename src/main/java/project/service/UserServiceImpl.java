package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.*;
import project.repository.ClientRepository;
import project.repository.EmployeeRepository;
import project.repository.RoleRepository;
import project.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private ClientRepository clientRepository;

        @Autowired
        private EmployeeRepository employeeRepository;

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;


        public Integer save(User user, Client client) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            final String uuid = UUID.randomUUID().toString();
            user.setToken(uuid);

            List<Role> roleList = roleRepository.findAll();
            user.setRoleList(roleList);
            userRepository.save(user);
            client.setUser(user);
            clientRepository.save(client);
            Employee employee = new Employee();
            employee.setFirstName(client.getFirstName());
            employee.setLastName(client.getLastName());
            employee.setPost(user.getUserName()+" mobileAppUser");
            return employeeRepository.save(employee).getId();
        }

        public User findByUserName(String email) {
            return userRepository.findByUserName(email);
        }


    public List<User> showAll(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User findByToken(String token){
        return userRepository.findByToken(token);
    }
    public List <Ticket> showTicketsByUserToken(String token){
          User user = userRepository.findByToken(token);
          return user.ticketList;
    }

    public List <Client> showClientsByUserToken(String token){
        User user = userRepository.findByToken(token);
        return user.clientList;
    }
    public User findById(final Long id){
        return userRepository.findById(id).get();

    }
}
