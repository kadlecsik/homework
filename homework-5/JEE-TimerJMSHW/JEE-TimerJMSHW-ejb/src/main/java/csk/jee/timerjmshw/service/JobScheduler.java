package csk.jee.timerjmshw.service;

import csk.jee.timerjmshw.dto.JobDTO;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;

@Stateless
@LocalBean
public class JobScheduler {

    @Inject
    StatisticsBean statisticsBean;

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:jboss/dzsobKju")
    private Queue queue;

    @Resource(lookup = "java:jboss/dzsobTopik")
    private Topic topic;

    private static final int NUMBER_OF_JOBS_PER_MINUTE = 10;

    private final Random randomGenerator = new Random();

    @Schedule(minute = "*/1", hour = "*", persistent = false)
    private void startJobs() {
        JMSProducer jmsProducer = jmsContext.createProducer();

        for (int i = 0; i < NUMBER_OF_JOBS_PER_MINUTE; ++i) {
            JobDTO dto = new JobDTO(UUID.randomUUID().toString(), randomGenerator.nextInt(5) + 1, LocalTime.now());
            ObjectMessage om = jmsContext.createObjectMessage(dto);
            jmsProducer.send(queue, om);
            statisticsBean.startJob(dto);
        }
        jmsProducer.send(topic, "Wake up!");
    }
}
