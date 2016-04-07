package csk.mobilewebshop.rest;

import csk.mobilewebshop.dto.UserDTO;
import csk.mobilewebshop.interceptor.BeanValidation;
import csk.mobilewebshop.service.UserManagementService;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kadlecsik
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@BeanValidation
public class UserRESTService {

    @Inject
    private UserManagementService userManagementService;

    @POST
    @Path("/")
    public UserDTO addUser(UserDTO user) {
        return userManagementService.addUser(user);
    }

    @DELETE
    @Path("/{username}")
    public UserDTO removeUser(@PathParam("username") String username) {
        return userManagementService.removeUser(username);
    }

    @PUT
    @Path("/{username}")
    public UserDTO editUser(@PathParam("username") String username, UserDTO user) {
        if (!user.getUserName().equals(username)) {
            throw new IllegalArgumentException("Username in JSON does not equal to " + username);
        } else {
            return userManagementService.editUser(user);
        }
    }

    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam("username") String username) {
        return userManagementService.getUser(username);
    }

    @GET
    @Path("/")
    public List<UserDTO> getUsers() {
        return new ArrayList<>(userManagementService.getUsers().values());
    }
    
    @POST
    @Path("/login")
    public int login(@Context HttpServletRequest request, UserDTO user) {
        HttpSession session = request.getSession(true);

        if (user.getPassword().equals(userManagementService.getUser(user.getUserName()).getPassword())) {
            session.setMaxInactiveInterval(-1);
            session.setAttribute("user", user.getUserName());
            return 1;
        } else {
            session.invalidate();
            return -1;
        }
    }
    
    @POST
    @Path("/logout")
    public void logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
    }
}
