package csk.mobilewebshop.rest;

import csk.mobilewebshop.dto.UserDTO;
import csk.mobilewebshop.exception.IllegalRequestException;
import csk.mobilewebshop.interceptor.BeanValidation;
import csk.mobilewebshop.service.UserManagementService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRESTService {

    @Inject
    private UserManagementService userManagementService;
    
    private static final String USER_NOT_EXIST = "User does not exist.";
    public static final String SESSION_USERNAME_KEY = "user";

    @POST
    @Path("/")
    @BeanValidation
    public UserDTO addUser(UserDTO user) {
        return userManagementService.addUser(user);
    }

    @DELETE
    @Path("/{username}")
    public UserDTO removeUser(@PathParam("username") String username) {
        UserDTO user = userManagementService.removeUser(username);
        if (user == null) {
            throw new IllegalRequestException(USER_NOT_EXIST);
        } else {
            return user;
        }
    }

    @PUT
    @Path("/{username}")
    @BeanValidation
    public UserDTO editUser(@PathParam("username") String username, UserDTO user) {
        if (!user.getUserName().equals(username)) {
            throw new IllegalRequestException("Username in JSON does not equal to " + username + ".");
        } else {
            return userManagementService.editUser(user);
        }
    }

    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam("username") String username) {
        if (userManagementService.getUser(username) == null) {
            throw new IllegalRequestException(USER_NOT_EXIST);
        } else {
            return userManagementService.getUser(username);
        }

    }

    @GET
    @Path("/")
    public List<UserDTO> getUsers() {
        return new ArrayList<>(userManagementService.getUsers().values());
    }

    @POST
    @Path("/login")
    public UserDTO login(@Context HttpServletRequest request, UserDTO user) {
        HttpSession session = request.getSession(true);

        UserDTO storedUser = userManagementService.getUser(user.getUserName());
        if (storedUser == null) {
            session.invalidate();
            throw new IllegalRequestException(USER_NOT_EXIST);
        } else if (storedUser.getPassword().equals(user.getPassword())) {
            session.setMaxInactiveInterval(3600);
            session.setAttribute(SESSION_USERNAME_KEY, user.getUserName());
            return storedUser;
        } else {
            session.invalidate();
            throw new IllegalRequestException("Wrong password.");
        }
    }

    @POST
    @Path("/logout")
    public void logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
    }

}
