/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csk.mobilewebshop.rest;

import csk.mobilewebshop.dto.UserDTO;
import csk.mobilewebshop.service.UserManagementService;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kadlecsik
 */
@Path("/users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRESTService {
    
    @POST
    public UserDTO addUser(UserDTO user)
    {
            return UserManagementService.addUser(user);
    }
}
