/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author kadlecsik
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(csk.amusementpark.exception.EJBTransactionRolledbackExceptionMapper.class);
        resources.add(csk.amusementpark.exception.GeneralExceptionMapper.class);
        resources.add(csk.amusementpark.exception.IllegalRequestExceptionMapper.class);
        resources.add(csk.amusementpark.exception.ValidationExceptionMapper.class);
        resources.add(csk.amusementpark.rest.AmusementParkRESTService.class);
        resources.add(csk.amusementpark.rest.GuestBookRESTService.class);
        resources.add(csk.amusementpark.rest.ReportRESTService.class);
        resources.add(csk.amusementpark.rest.VisitorRESTRestService.class);
    }
    
}
