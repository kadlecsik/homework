/**
 *
 * @author Csaba Kadlecsik <kadlecsik@outlook.com>
 */
package csk.mobilewebshop.service;

import csk.mobilewebshop.dto.UserDTO;
import csk.mobilewebshop.interceptor.BeanValidation;
import java.time.LocalDate;
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

@Singleton
@LocalBean
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@BeanValidation
public class UserManagementService {

    private final Map<String, UserDTO> users = new HashMap<>();

    @PostConstruct
    private void initialize() {
        
        UserDTO admin = new UserDTO();
        admin.setAdmin(true);
        admin.setUserName("admin");
        admin.setPassword("admin");
        admin.setRegistrationDate(LocalDate.now());

        users.put(admin.getUserName(), admin);

        UserDTO user = new UserDTO();
        user.setAdmin(false);
        user.setUserName("user");
        user.setPassword("user");
        user.setRegistrationDate(LocalDate.now());

        users.put(user.getUserName(), user);
        
        System.out.println(users.toString());
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
    
    @Lock(LockType.READ)
    public UserDTO getUser(String username) {
        return users.get(username);
    }
    
    @Lock(LockType.READ)
    public Map<String, UserDTO> getUsers() {
        System.out.println(users.toString());
        return users;
    }

}
