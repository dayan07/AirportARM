package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Client;
import project.entity.Employee;
import project.repository.EmployeeRepository;

import java.util.List;

@Component
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> showAllEmployees(){
        List <Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    public Employee getEmployeeById(Integer id){
        return employeeRepository.findById(id).get();
    }

    public Integer getEmployeeByPost(String post){
        return employeeRepository.findByPost(post).getId();
    }


    public Integer HasEmployeeTickets(Integer id) {
        Employee employeeToDelete = getEmployeeById(id);
        if(employeeToDelete.ticketList.size() == 0 ){
            return 1;
        }
        else {
            return -1;
        }
    }
}