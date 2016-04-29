package csk.amusementpark.bean.service;

import csk.amusementpark.bean.facade.AmusementParkFacade;
import csk.amusementpark.bean.facade.EntityFacade;
import csk.amusementpark.bean.facade.MachineFacade;
import csk.amusementpark.bean.facade.VisitorFacade;
import csk.amusementpark.dto.MachineDTO;
import csk.amusementpark.dto.VisitorDTO;
import csk.amusementpark.entity.amusementpark.AmusementPark;
import csk.amusementpark.entity.machine.Machine;
import csk.amusementpark.entity.visitor.Status;
import csk.amusementpark.entity.visitor.Visitor;
import csk.amusementpark.exception.IllegalRequestException;
import csk.amusementpark.interceptor.ValidatorInterceptor;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
@LocalBean
public class MachineService {

    @Inject
    private EntityFacade ef;

    @Inject
    private AmusementParkFacade af;

    @Inject
    private MachineFacade mf;

    @Inject
    private VisitorFacade vf;

    @Inject
    private ParserService ps;

    private void checkMachineInPark(Machine m, String parkIdS) {
        if (!m.getAmusementPark().getId().equals(ps.parseStringId(parkIdS))) {
            throw new IllegalRequestException("There no machine with id " + m.getId().toString() + " in this amusement park.");
        }
    }

    @Interceptors(ValidatorInterceptor.class)
    public MachineDTO addMachine(String parkIdS, MachineDTO dto) {

        AmusementPark park = af.getById(ps.parseStringId(parkIdS));

        Machine m = new Machine(dto);

        if (af.getAllocatedAreaById(park.getId()) + m.getMachineSize() > park.getTotalArea()) {
            throw new IllegalRequestException("Can not add machine " + dto.getName() + ", because it would not fit into the amusement park.");
        }

        if (dto.getPrice() > park.getFinancialCapital()) {
            throw new IllegalRequestException("Can not add machine " + dto.getName() + ", because the amusemant park cannot afford to buy it.");
        }

        m.setAmusementPark(park);
        park.getMachines().add(m);

        park.setFinancialCapital(park.getFinancialCapital() - dto.getPrice());

        ef.create(m);
        ef.update(park);

        return new MachineDTO(m);

    }

    public MachineDTO deleteMachine(String parkIdS, String machineIdS) {

        AmusementPark park = af.getById(ps.parseStringId(parkIdS));
        Machine m = mf.getById(ps.parseStringId(machineIdS));

        checkMachineInPark(m, parkIdS);

        if (!m.getVisitors().isEmpty()) {
            throw new IllegalRequestException("This machine cannot be deleted, because there are active visitors on it.");
        }

        park.getMachines().remove(m);
        ef.update(park);

        ef.delete(m);

        return new MachineDTO(m);
    }

    public List<MachineDTO> getMachines(String parkIdS) {

        List ret = new LinkedList<>();

        mf.getAllByParkId(ps.parseStringId(parkIdS)).stream().forEach(m -> ret.add(new MachineDTO(m)));

        return ret;
    }

    public MachineDTO getMachine(String parkIdS, String machineIdS) {

        Machine m = mf.getById(ps.parseStringId(machineIdS));

        checkMachineInPark(m, parkIdS);

        return new MachineDTO(m);
    }

    public MachineDTO updateMachine(String parkIdS, String machineIdS, MachineDTO dto) {

        Machine m = mf.getById(ps.parseStringId(machineIdS));

        checkMachineInPark(m, parkIdS);

        if (dto.getMachineType() != null) {
            throw new IllegalRequestException("The type of the machine cannot be changed this way. Delete the machine and create it again.");
        }

        if (dto.getMachineSize() != null) {
            if (af.getAllocatedAreaById(m.getAmusementPark().getId()) - m.getMachineSize() + dto.getMachineSize() > m.getAmusementPark().getTotalArea()) {
                throw new IllegalRequestException("Machine size cannot be changed to " + dto.getMachineSize() + " because the machine would not fit into the amusement park.");
            }

            m.setMachineSize(dto.getMachineSize());
        }

        if (dto.getSeats() != null) {
            if (m.getVisitors().size() > dto.getSeats()) {
                throw new IllegalRequestException("The number of seats cannot be changed to " + dto.getSeats() + " because there are " + m.getVisitors().size() + " people on the machine right now.");
            }
            m.setSeats(dto.getSeats());
        }

        m.setMinimumRequiredAge(dto.getMinimumRequiredAge() == null ? m.getMinimumRequiredAge() : dto.getMinimumRequiredAge());
        m.setName(dto.getName() == null ? m.getName() : dto.getName());
        m.setTicketPrice(dto.getTicketPrice() == null ? m.getTicketPrice() : dto.getTicketPrice());

        ef.update(m);

        return new MachineDTO(m);
    }

    public VisitorDTO getOn(String parkIdS, String machineIdS, VisitorDTO dto) {
        Visitor v = vf.getById(dto.getId());
        AmusementPark park = af.getById(ps.parseStringId(parkIdS));
        Machine m = mf.getById(ps.parseStringId(machineIdS));

        if (!m.getAmusementPark().equals(park)) {
            throw new IllegalRequestException("The given machine is not in this amusement park.");
        }
        if (!v.getInAmusementPark().equals(park) || !v.getActive()) {
            throw new IllegalRequestException("The visitor is not in this amusement park.");
        }
        if (v.getMoney() < m.getTicketPrice()) {
            throw new IllegalRequestException("The visitor cannot afford to buy ticket to this machine.");
        }
        if (v.getOnMachine() != null) {
            throw new IllegalRequestException("The visitor is already on a machine.");
        }
        if (v.getAge() < m.getMinimumRequiredAge()) {
            throw new IllegalRequestException("The visitor is too young for this machine.");
        }
        if (m.getVisitors().size() >= m.getSeats()) {
            throw new IllegalRequestException("The machine is full.");
        }

        v.setOnMachine(m);
        v.setStatus(Status.ON_MACHINE);
        m.getVisitors().add(v);

        ef.update(m);
        ef.update(v);

        return new VisitorDTO(v);

    }

    public VisitorDTO getOff(String parkIdS, String machineIdS, VisitorDTO dto) {
        Visitor v = vf.getById(dto.getId());
        AmusementPark park = af.getById(ps.parseStringId(parkIdS));
        Machine m = mf.getById(ps.parseStringId(machineIdS));

        if (!m.getAmusementPark().equals(park)) {
            throw new IllegalRequestException("The given machine is not in this amusement park.");
        }
        if (!v.getInAmusementPark().equals(park) || !v.getActive()) {
            throw new IllegalRequestException("The visitor is not in this amusement park.");
        }
        if (!v.getOnMachine().equals(m)) {
            throw new IllegalRequestException("The visitor is not on this machine.");
        }

        m.getVisitors().remove(v);
        v.setOnMachine(null);
        v.setStatus(Status.RESTING);

        ef.update(m);
        ef.update(v);

        return new VisitorDTO(v);
    }

    public Collection<VisitorDTO> getUsersByMachine(String machineIdS) {
        List<VisitorDTO> ret = new LinkedList<>();
        
        vf.getVisitorsByMachineId(ps.parseStringId(machineIdS)).stream().forEach(v ->  ret.add(new VisitorDTO(v)));
        
        return ret;
    }


}
