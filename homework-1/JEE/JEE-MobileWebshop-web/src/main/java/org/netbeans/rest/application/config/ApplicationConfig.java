package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Csaba Kadlecsik <kadlecsik@outlook.com>
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
        resources.add(csk.mobilewebshop.exception.GeneralExceptionMapper.class);
        resources.add(csk.mobilewebshop.exception.IllegalRequestExceptionMapper.class);
        resources.add(csk.mobilewebshop.exception.ValidationExceptionMapper.class);
        resources.add(csk.mobilewebshop.rest.CartRESTService.class);
        resources.add(csk.mobilewebshop.rest.InventoryRESTService.class);
        resources.add(csk.mobilewebshop.rest.UserRESTService.class);
    }
    
}
