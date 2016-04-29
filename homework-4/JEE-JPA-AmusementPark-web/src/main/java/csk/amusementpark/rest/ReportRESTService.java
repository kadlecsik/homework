package csk.amusementpark.rest;

import csk.amusementpark.bean.service.AmusementParkService;
import csk.amusementpark.bean.service.MachineService;
import csk.amusementpark.dto.MessageDTO;
import csk.amusementpark.dto.VisitorDTO;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Path("/reports")
public class ReportRESTService {

    @Inject
    private MachineService ms;

    @Inject
    private AmusementParkService aps;

    @GET
    @Path("/machine/{machine_id}/users")
    public Collection<VisitorDTO> getUsersByMachine(@PathParam("machine_id") String machineId) {

        return ms.getUsersByMachine(machineId);
    }

    @GET
    @Path("/park/{park_id}/resting")
    public MessageDTO getRestingUsers(@PathParam("park_id") String parkId) {

        return aps.getNumberOfRestingVisitors(parkId);
    }

    @GET
    @Path("/park/{park_id}/enoughmoney")
    public Collection<VisitorDTO> getVisitorsWithEnoughMoney(@PathParam("park_id") String parkId) {

        return aps.getVisitorsWithEnoughMoney(parkId);
    }
}
