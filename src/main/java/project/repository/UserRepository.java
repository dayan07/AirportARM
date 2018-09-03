package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String email);
    User findByToken(String token);
}