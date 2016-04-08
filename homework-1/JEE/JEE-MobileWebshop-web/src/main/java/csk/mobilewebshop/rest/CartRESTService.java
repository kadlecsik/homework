package csk.mobilewebshop.rest;

import csk.mobilewebshop.dto.MobileDTO;
import csk.mobilewebshop.exception.IllegalRequestException;
import csk.mobilewebshop.service.CartService;
import csk.mobilewebshop.service.InventoryService;
import csk.mobilewebshop.service.UserManagementService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SessionScoped
public class CartRESTService implements Serializable{

    @Inject
    private CartService cartService;

    @Inject
    private InventoryService inventoryService;

    @Inject
    private UserManagementService userManagementService;
    
    private static final String NO_USER_ERR_MSG = "No user logged in.";

    @POST
    @Path("/add")
    public List<MobileDTO> addToCart(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(3600);

        Object username = session.getAttribute(UserRESTService.SESSION_USERNAME_KEY);
        if ((username == null || !(username instanceof String)) || userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new IllegalRequestException(NO_USER_ERR_MSG);
        } else {
            return cartService.addToCart(mobile);
        }
    }

    @GET
    @Path("/checkout")
    public List<MobileDTO> checkout(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(3600);

        Object username = session.getAttribute(UserRESTService.SESSION_USERNAME_KEY);
        if ((username == null || !(username instanceof String)) || userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new IllegalRequestException(NO_USER_ERR_MSG);
        } else {
            List<MobileDTO> ret = cartService.getProducts();
            cartService.checkout();
            request.getSession().invalidate();
            return ret;
        }
    }

    @GET
    @Path("/")
    public List<MobileDTO> showCart(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(3600);

        Object username = session.getAttribute(UserRESTService.SESSION_USERNAME_KEY);
        if ((username == null || !(username instanceof String)) || userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new IllegalRequestException(NO_USER_ERR_MSG);
        } else {
            return cartService.getProducts();
        }

    }
}
