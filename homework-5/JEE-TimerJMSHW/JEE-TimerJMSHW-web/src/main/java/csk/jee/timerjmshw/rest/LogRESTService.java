package csk.jee.timerjmshw.rest;

import csk.jee.timerjmshw.util.Pair;
import csk.jee.timerjmshw.service.StatisticsBean;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/log")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LogRESTService {

    @Inject
    private StatisticsBean statisticsBean;

    @GET
    @Path("/")
    public List<Pair<String, Boolean>> getStatistics() {
        return statisticsBean.getLog();
    }

}
