package csk.amusementpark.rest;

import csk.amusementpark.dto.GuestBookEntryDTO;
import java.util.List;
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
@Path("/guestbook")
public class GuestBookRESTService {

    @Inject
    private csk.amusementpark.bean.service.GuestBookService gs;

    //GuestBookEntry CRUD
    @Path("/park/{park_id}/visitor/{visitor_id}")
    @POST
    public GuestBookEntryDTO addBookEntry(@PathParam("park_id") String parkId, @PathParam("visitor_id") String visitorId, GuestBookEntryDTO dto) {
        return gs.addBookEntry(parkId, visitorId, dto);
    }

    @Path("/park/{park_id}/visitor/{visitor_id}")
    @GET
    public List<GuestBookEntryDTO> getBookEntryForOneUser(@PathParam("park_id") String parkId, @PathParam("visitor_id") String visitorId) {
        return gs.getBookEntriesForOneUser(parkId, visitorId);
    }

    @Path("/park/{park_id}")
    @GET
    public List<GuestBookEntryDTO> getBookEntriesForPark(@PathParam("park_id") String parkId) {
        return gs.getBookEntriesForPark(parkId);
    }

    @Path("/{entry_id}")
    @GET
    public GuestBookEntryDTO getBookEntry(@PathParam("entry_id") String entryId) {
        return gs.getBookEntry(entryId);
    }

    @Path("/{entry_id}")
    @PUT
    public GuestBookEntryDTO updateBookEntry(@PathParam("entry_id") String entryId, GuestBookEntryDTO dto) {
        return gs.updateBookEntry(entryId, dto);
    }

    @Path("/{entry_id}")
    @DELETE
    public GuestBookEntryDTO deleteBookEntry(@PathParam("entry_id") String entryId) {
        return gs.deleteBookEntry(entryId);
    }
}
