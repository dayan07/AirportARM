package project.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    String token;
    Integer empId;


    public UserResponse( String token, Integer empId) {
        super();
        this.token = token;
        this.empId = empId;
    }
}
