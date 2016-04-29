package csk.amusementpark.bean.service;

import csk.amusementpark.bean.facade.AmusementParkFacade;
import csk.amusementpark.bean.facade.EntityFacade;
import csk.amusementpark.bean.facade.VisitorFacade;
import csk.amusementpark.dto.VisitorDTO;
import csk.amusementpark.entity.amusementpark.AmusementPark;
import csk.amusementpark.entity.visitor.Status;
import csk.amusementpark.entity.visitor.Visitor;
import csk.amusementpark.exception.IllegalRequestException;
import csk.amusementpark.interceptor.ValidatorInterceptor;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
public class VisitorService {

    @Inject
    private EntityFacade ef;

    @Inject
    private VisitorFacade vf;

    @Inject
    private AmusementParkFacade af;

    @Inject
    private ParserService ps;
    @Interceptors(ValidatorInterceptor.class)
    public VisitorDTO addVisitor(String parkIdS, VisitorDTO dto) {
        
        AmusementPark park = af.getById(ps.parseStringId(parkIdS));
        
        Visitor v = new Visitor(dto);
        
        v.setInAmusementPark(park);
        park.getVisitors().add(v);
        
        ef.create(v);
        ef.update(park);
        
        return new VisitorDTO(v);

    }

    public VisitorDTO deleteVisitor(String visitorIdS) {

        Visitor v = vf.getById(ps.parseStringId(visitorIdS));

        v.setActive(false);

        ef.update(v);

        return new VisitorDTO(v);
    }

    public VisitorDTO updateVisitor(String visitorIdS, VisitorDTO dto) {

        Visitor v = vf.getById(ps.parseStringId(visitorIdS));

        if (dto.getInAmusementPark() != null || dto.getOnMachine() != null || dto.getActive() != null) {
            throw new IllegalRequestException("The amusement park, machine and active/passive status cannot be changed this way. Use the dedicated functions instead.");
        }

        v.setAge(dto.getAge() == null ? v.getAge() : dto.getAge());
        v.setDateOfEntry(dto.getDateOfEntry() == null ? v.getDateOfEntry() : dto.getDateOfEntry());
        v.setMoney(dto.getMoney() == null ? v.getMoney() : dto.getMoney());

        if (dto.getStatus() != null) {
            if (!v.getActive()) {
                throw new IllegalRequestException("Status of a passive visitor cannot be changed.");
            }
            switch (dto.getStatus().toLowerCase()) {
                case "resting":
                    v.setStatus(Status.RESTING);
                    break;
                case "on_machine":
                case "onmachine":
                case "on machine":
                case "on-machine":
                    v.setStatus(Status.ON_MACHINE);
                    break;
                default:
                    throw new IllegalRequestException("Visitor status " + dto.getStatus() + " does not exist.");
            }
        }

        ef.update(v);

        return new VisitorDTO(v);
    }

    public List<VisitorDTO> getVisitors() {

        List<VisitorDTO> ret = new LinkedList<>();

        vf.getAll().stream().forEach(v -> ret.add(new VisitorDTO(v)));

        return ret;
    }

    public VisitorDTO getVisitor(String visitorIdS) {

        return new VisitorDTO(vf.getById(ps.parseStringId(visitorIdS)));
    }

}
