package csk.jee.timerjmshw.bean.mdb;

import csk.jee.timerjmshw.dto.JobDTO;
import csk.jee.timerjmshw.service.StatisticsBean;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

public abstract class AbstractWorker implements MessageListener {

    @Inject
    StatisticsBean statisticsBean;

    @Resource(lookup = "java:jboss/dzsobKju")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    protected abstract double getMultiplier();

    @Override
    public void onMessage(Message message) {
        JMSConsumer jmsc = jmsContext.createConsumer(queue);
        ObjectMessage om = (ObjectMessage) jmsc.receive(5000);

        while (om != null) {
            try {
                JobDTO dto = (JobDTO) om.getObject();
                TimeUnit.SECONDS.sleep((long) (dto.getEstimatedDuration() * getMultiplier()));
                statisticsBean.endJob(new JobDTO(dto.getId(), LocalTime.now()));
            } catch (InterruptedException | JMSException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }

            om = (ObjectMessage) jmsc.receive(5000);
        }
    }

}
