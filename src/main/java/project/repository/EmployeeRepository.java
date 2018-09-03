package project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Client;
import project.entity.Employee;



@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {
    Employee findByPost(String post);
}