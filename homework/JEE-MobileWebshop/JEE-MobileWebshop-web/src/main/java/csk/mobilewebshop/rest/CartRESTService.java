package csk.mobilewebshop.rest;

import csk.mobilewebshop.dto.MobileDTO;
import csk.mobilewebshop.interceptor.BeanValidation;
import csk.mobilewebshop.service.CartService;
import csk.mobilewebshop.service.InventoryService;
import csk.mobilewebshop.service.UserManagementService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
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

/**
 *
 * @author kadlecsik
 */
@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateful
@BeanValidation
public class CartRESTService implements Serializable{
    
    
    @Inject
    CartService cartService;

    @EJB
    InventoryService inventoryService;

    @EJB
    UserManagementService userManagementService;

    @POST
    @Path("/add")
    public boolean addToCart(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(200);
        System.out.println(session.getId());
        Object username = session.getAttribute("username");

        if (username != null && username instanceof String && userManagementService.getUser(username.toString()) != null) {
            if (inventoryService.isMobileAvaible(mobile)) {
                cartService.addToCart(mobile);
                return true;
            }
        }
        return false;
    }

    @GET
    @Path("/checkout")
    public void checkout(@Context HttpServletRequest request) {
        cartService.checkout();
        request.getSession().invalidate();
    }

    @GET
    @Path("/")
    public List<MobileDTO> showCart() {
        return cartService.getProducts();
    }

}
