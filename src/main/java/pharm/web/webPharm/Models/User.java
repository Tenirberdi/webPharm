//package pharm.web.webPharm.Models;
//
//import java.util.*;
//import javax.persistence.*;
//
//@Entity
//@Table(name = "users")
//public class User {
//
//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Role> roles = new HashSet<>();
//
//    public boolean hasRole(String roleName) {
//        Iterator<Role> iterator = this.roles.iterator();
//        while (iterator.hasNext()) {
//            Role role = iterator.next();
//            if (role.getName().equals(roleName)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    // other fields, getters and setters are not shown for brevity
//}
