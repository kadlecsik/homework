package csk.amusementpark.rest;

import csk.amusementpark.bean.service.VisitorService;
import csk.amusementpark.dto.VisitorDTO;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Path("/visitor")
public class VisitorRESTRestService {

    @Inject
    private VisitorService vs;

    //Visitor CRUD
    @POST
    @Path("/park/{park_id}")
    public VisitorDTO addVisitor(@PathParam("park_id") String parkId, VisitorDTO dto) {
        return vs.addVisitor(parkId, dto);
    }

    @GET
    @Path("/")
    public Collection<VisitorDTO> getVisitors() {
        return vs.getVisitors();
    }

    @GET
    @Path("/{visitor_id}")
    public VisitorDTO getVisitor(@PathParam("visitor_id") String visitorId) {
        return vs.getVisitor(visitorId);
    }

    @PUT
    @Path("/{visitor_id}")
    public VisitorDTO updateVisitor(@PathParam("visitor_id") String visitorId, VisitorDTO dto) {
        return vs.updateVisitor(visitorId, dto);
    }

    @DELETE
    @Path("/{visitor_id}")
    public VisitorDTO deleteVisitor(@PathParam("visitor_id") String visitorId) {

        return vs.deleteVisitor(visitorId);
    }
}
