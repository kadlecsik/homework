package csk.jee.timerjmshw.bean.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

@MessageDriven(mappedName = "java:jboss/dzsobTopik",
        activationConfig
        = {
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
            @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/dzsobTopik"),
            @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true")
        })
public class LazyWorker extends AbstractWorker {

    @Override
    protected double getMultiplier() {
        return 2.0;
    }
}
