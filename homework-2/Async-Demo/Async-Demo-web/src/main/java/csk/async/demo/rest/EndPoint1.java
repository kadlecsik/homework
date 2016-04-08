package csk.async.demo.rest;

import csk.async.demo.AsyncService;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Csaba Kadlecsik <kadlecsik@outlook.com>
 */
@Path("/ep1")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@SessionScoped
public class EndPoint1 implements Serializable {

    @Inject
    AsyncService asyncService;

    @GET
    @Path("/")
    //http://localhost:8080/Async-Demo-web/webresources/ep1
    public String doSomething(@Context HttpServletRequest request) throws InterruptedException, ExecutionException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(3600);
        Future<Integer> i = asyncService.blackMagic();
        Future<Integer> j = asyncService.blackMagic();
        i.cancel(true);
        Integer i1 = i.get();
        Integer i2 = j.get();

        return (i1.toString() + " " + i2.toString());
    }

}
