package project.entity;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Builder;
//import lombok.Value;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.OneToMany;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import static java.util.Objects.requireNonNull;



import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String userName;
    String password;
    String token;

    @OneToMany(mappedBy="user")
    public List<Client> clientList;

    @OneToMany(mappedBy="user")
    public List<Ticket> ticketList;

    @ManyToMany
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "id"))
    public List<Role> roleList;





}


//@Value
//@Builder
//public class User implements UserDetails  {
//    private static final long serialVersionUID = 2396654715019746670L;
//
//    String id;
//    String username;
//    String password;
////
////    @OneToMany(mappedBy="user")
////    public List<Client> clientList;
////
////    @OneToMany(mappedBy="user")
////    public List<Ticket> ticketList;
//
//    @JsonCreator
//    User(@JsonProperty("id") final String id,
//         @JsonProperty("username") final String username,
//         @JsonProperty("password") final String password) {
//        super();
//        this.id = requireNonNull(id);
//        this.username = requireNonNull(username);
//        this.password = requireNonNull(password);
//    }
//
//    @JsonIgnore
//    @Override
//    public Collection<GrantedAuthority> getAuthorities() {
//        return new ArrayList<>();
//    }
//
//    @JsonIgnore
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}
