package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Client;

@Repository
public interface ClientRepository
        extends JpaRepository<Client, Integer> {
    Client findByPassportNumber(String passportNumber);
}
