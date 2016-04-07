package csk.mobilewebshop.rest;

import csk.mobilewebshop.dto.MobileDTO;
import csk.mobilewebshop.interceptor.BeanValidation;
import csk.mobilewebshop.service.InventoryService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/mobiles")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@BeanValidation
public class InventoryRESTService {
    
    @EJB
    InventoryService inventoryService;
    
    @GET
    @Path("/")
    public List<MobileDTO> getMobiles()
    {
        return inventoryService.getMobileList();
    }
}
