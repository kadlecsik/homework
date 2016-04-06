package csk.mobilewebshop.service;

import csk.mobilewebshop.dto.UserDTO;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateful;

/**
 *
 * @author Csaba Kadlecsik <kadlecsik@outlook.com>
 */
@Singleton
//@LocalBean
@Startup
//@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UserManagementService {

    private final Map<String, UserDTO> users = new HashMap<>();

    @PostConstruct
    private void initialize() {
        System.out.println("Singleton "+ this.toString());
    }

    @Lock(LockType.WRITE)
    public UserDTO addUser(UserDTO user) {
        users.put(user.getUserName(), user);
        return user;
    }

    @Lock(LockType.WRITE)
    public UserDTO removeUser(String username) {
        return users.remove(username);
    }

    @Lock(LockType.WRITE)
    public UserDTO editUser(UserDTO user) {
        return users.put(user.getUserName(), user);
    }

    public UserDTO getUser(String username) {
        return users.get(username);
    }

}
