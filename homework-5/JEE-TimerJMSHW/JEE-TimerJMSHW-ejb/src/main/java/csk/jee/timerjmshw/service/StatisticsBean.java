package csk.jee.timerjmshw.service;

import csk.jee.timerjmshw.util.Pair;
import csk.jee.timerjmshw.dto.JobDTO;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@LocalBean
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
public class StatisticsBean {

    private final ConcurrentMap<String, LocalTime> runningJobs = new ConcurrentHashMap<>();

    private final ConcurrentMap<String, Boolean> finishedJobs = new ConcurrentHashMap<>();

    private static final Logger LOG = Logger.getLogger(StatisticsBean.class.getName());

    public void startJob(JobDTO dto) {
        LOG.log(Level.INFO, "Job {0} was added to the queue.", dto.getId());
        runningJobs.put(dto.getId(), dto.getTimestamp());
    }

    public void endJob(JobDTO dto) {
        if (runningJobs.containsKey(dto.getId())) {
            long seconds = ChronoUnit.SECONDS.between(runningJobs.get(dto.getId()), dto.getTimestamp());
            if (Math.abs(seconds) <= 5) {
                LOG.log(Level.INFO, "Job {0} is finished. Status : SUCCESS", dto.getId());
                finishedJobs.put(dto.getId(), true);
            } else {
                LOG.log(Level.INFO, "Job {0} is finished. Status : FAILURE", dto.getId());
                finishedJobs.put(dto.getId(), false);
            }

            runningJobs.remove(dto.getId());
        }
    }

    public List<Pair<String, Boolean>> getLog() {
        List<Pair<String, Boolean>> ret = new LinkedList<>();
        finishedJobs.keySet().stream().forEach(key -> ret.add(new Pair<>(key, finishedJobs.get(key))));
        return ret;
    }
}
