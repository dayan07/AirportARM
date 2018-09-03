package project.service;

import org.springframework.stereotype.Service;
import project.entity.Client;
import project.entity.Ticket;
import project.entity.User;

import java.util.List;

public interface UserService {
    Integer save(User user, Client client);
    List <Ticket> showTicketsByUserToken(String token);
    List <Client> showClientsByUserToken(String token);
    User findByToken(String token);
    User findByUserName(String email);
    User findById(Long id);
    List<User> showAll();
}
