package csk.amusementpark.rest;


import csk.amusementpark.bean.service.AmusementParkService;
import csk.amusementpark.bean.service.MachineService;
import csk.amusementpark.dto.AmusementParkDTO;
import csk.amusementpark.dto.MachineDTO;
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
@Path("/park")
public class AmusementParkRESTService {

    @Inject
    private AmusementParkService aps;

    @Inject
    private MachineService ms;

    @POST
    @Path("/")
    public AmusementParkDTO addPark(AmusementParkDTO dto) {
        return aps.createPark(dto);
    }

    @GET
    @Path("/")
    public Collection<AmusementParkDTO> getParks() {
        return aps.getParks();
    }

    @GET
    @Path("/{park_id}")
    public AmusementParkDTO getPark(@PathParam("park_id") String id) {
        return aps.getPark(id);
    }

    @PUT
    @Path("/{park_id}")
    public AmusementParkDTO updatePark(@PathParam("park_id") String id, AmusementParkDTO dto) {
        return aps.updatePark(id, dto);
    }

    @DELETE
    @Path("/{park_id}")
    public AmusementParkDTO deletePark(@PathParam("park_id") String id) {
        return aps.deletePark(id);
    }

    @POST
    @Path("/{park_id}/machine")
    public MachineDTO addMachine(@PathParam("park_id") String parkId, MachineDTO dto) {
        return ms.addMachine(parkId, dto);
    }

    @GET
    @Path("/{park_id}/machine")
    public Collection<MachineDTO> getMachines(@PathParam("park_id") String id) {
        return ms.getMachines(id);
    }

    @GET
    @Path("/{park_id}/machine/{machine_id}")
    public MachineDTO getMachine(@PathParam("park_id") String parkId, @PathParam("machine_id") String machineId) {
        return ms.getMachine(parkId, machineId);
    }

    @PUT
    @Path("/{park_id}/machine/{machine_id}")
    public MachineDTO updateMachine(@PathParam("park_id") String parkId, @PathParam("machine_id") String machineId, MachineDTO dto) {
        return ms.updateMachine(parkId, machineId, dto);
    }

    @DELETE
    @Path("/{park_id}/machine/{machine_id}")
    public MachineDTO deleteMachine(@PathParam("park_id") String parkId, @PathParam("machine_id") String machineId) {
        return ms.deleteMachine(parkId, machineId);
    }
    //-----------
    @POST
    @Path("/{park_id}/checkin")
    public VisitorDTO checkIn(@PathParam("park_id") String parkId, VisitorDTO dto) {
        return aps.checkIn(parkId, dto);
    }
    
    @POST
    @Path("/{park_id}/checkout")
    public VisitorDTO checkOut(@PathParam("park_id") String parkId, VisitorDTO dto) {
        return aps.checkOut(parkId, dto);
    }
    
    @POST
    @Path("/{park_id}/machine/{machine_id}/geton")
    public VisitorDTO getOn(@PathParam("park_id") String parkId, @PathParam("machine_id") String machineId, VisitorDTO dto) {
        return ms.getOn(parkId,machineId,dto);
    }
    
    @POST
    @Path("/{park_id}/machine/{machine_id}/getoff")
    public VisitorDTO getOff(@PathParam("park_id") String parkId, @PathParam("machine_id") String machineId, VisitorDTO dto) {
        return ms.getOff(parkId,machineId,dto);
    }
    
}
