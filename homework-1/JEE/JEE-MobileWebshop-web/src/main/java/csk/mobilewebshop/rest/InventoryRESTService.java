package csk.mobilewebshop.rest;

import csk.mobilewebshop.dto.MobileDTO;
import csk.mobilewebshop.exception.IllegalRequestException;
import csk.mobilewebshop.interceptor.BeanValidation;
import csk.mobilewebshop.service.InventoryService;
import csk.mobilewebshop.service.UserManagementService;
import java.util.List;

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

@Path("/mobiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryRESTService {

    @Inject
    private InventoryService inventoryService;

    @Inject
    private UserManagementService userManagementService;

    @GET
    @Path("/")
    public List<MobileDTO> getMobiles() {
        return inventoryService.getMobileList();
    }

    @POST
    @BeanValidation
    @Path("/")
    public MobileDTO addMobile(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(3600);
        Object username = session.getAttribute(UserRESTService.SESSION_USERNAME_KEY);
        if (username == null || userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new IllegalRequestException("No user logged in.");
        } else if (!userManagementService.getUser(username.toString()).isAdmin()) {
            session.invalidate();
            throw new IllegalRequestException("Permission denied to add new mobile.");
        } else {
            return inventoryService.addMobile(mobile);
        }

    }

}
