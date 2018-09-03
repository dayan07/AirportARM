package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Client;
import project.entity.Flight;
import project.entity.Plane;
import project.repository.ClientRepository;
import project.repository.FlightRepository;

import java.util.List;

@Component
@Transactional
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public void saveClient(Client client){

        clientRepository.save(client);
    }

    public List<Client> showAllClients(){


        List <Client> clientList = clientRepository.findAll();
        return clientList;
    }

    public Client getClientById(Integer id){

        return clientRepository.findById(id).get();

    }

    public Integer HasClientTickets(Integer id) {
        Client clientToDelete = getClientById(id);
        if(clientToDelete.ticketList.size() == 0 ){
            return 1;
        }
        else {
            return -1;
        }

    }




}
