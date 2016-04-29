package csk.amusementpark.bean.service;

import csk.amusementpark.bean.facade.AmusementParkFacade;
import csk.amusementpark.bean.facade.EntityFacade;
import csk.amusementpark.bean.facade.VisitorFacade;
import csk.amusementpark.dto.AmusementParkDTO;
import csk.amusementpark.dto.MessageDTO;
import csk.amusementpark.dto.VisitorDTO;
import csk.amusementpark.entity.amusementpark.AmusementPark;
import csk.amusementpark.entity.visitor.Status;
import csk.amusementpark.entity.visitor.Visitor;
import csk.amusementpark.exception.IllegalRequestException;
import csk.amusementpark.interceptor.ValidatorInterceptor;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
@LocalBean
public class AmusementParkService {

    @Inject
    private EntityFacade ef;

    @Inject
    private AmusementParkFacade af;

    @Inject
    private VisitorFacade vf;

    @Inject
    private ParserService ps;
    

    @Interceptors(ValidatorInterceptor.class)
    public AmusementParkDTO createPark(AmusementParkDTO dto) {

        return new AmusementParkDTO(ef.create(new AmusementPark(dto)));
    }

    public List<AmusementParkDTO> getParks() {

        List<AmusementParkDTO> ret = new LinkedList<>();

        af.getAll().stream().forEach(a -> ret.add(new AmusementParkDTO(a)));

        return ret;
    }

    public AmusementParkDTO getPark(String parkIdS) {

        return new AmusementParkDTO(af.getById(ps.parseStringId(parkIdS)));
    }

    public AmusementParkDTO updatePark(String parkIdS, AmusementParkDTO dto) {

        AmusementPark park = af.getById(ps.parseStringId(parkIdS));

        park.setFinancialCapital(dto.getFinancialCapital() == null ? park.getFinancialCapital() : dto.getFinancialCapital());
        park.setName(dto.getName() == null ? park.getName() : dto.getName());
        park.setEntryFee(dto.getEntryFee() == null ? park.getEntryFee() : dto.getEntryFee());

        if (dto.getTotalArea() != null) {
            if (af.getAllocatedAreaById(park.getId()) > dto.getTotalArea()) {
                throw new IllegalRequestException("The given size is less than the already allocated area.");
            }
            park.setTotalArea(dto.getTotalArea());
        }

        if (dto.getAddress() != null) {
            park.getAddress().setZipCode(dto.getAddress().getZipCode() == null ? park.getAddress().getZipCode() : dto.getAddress().getZipCode());
            park.getAddress().setCountry(dto.getAddress().getCountry() == null ? park.getAddress().getCountry() : dto.getAddress().getCountry());
            park.getAddress().setCity(dto.getAddress().getCity() == null ? park.getAddress().getCity() : dto.getAddress().getCity());
            park.getAddress().setStreet(dto.getAddress().getStreet() == null ? park.getAddress().getStreet() : dto.getAddress().getStreet());
            park.getAddress().setNumber(dto.getAddress().getNumber() == null ? park.getAddress().getNumber() : dto.getAddress().getNumber());
        }

        ef.update(park);

        return new AmusementParkDTO(park);
    }

    public AmusementParkDTO deletePark(String parkIdS) {

        if (af.getNumberVisitorsById(ps.parseStringId(parkIdS)) > 0) {
            throw new IllegalRequestException("This park cannot be deleted, because there are active visitors checked in.");
        }

        return new AmusementParkDTO(ef.delete(af.getById(ps.parseStringId(parkIdS))));
    }

    public VisitorDTO checkIn(String parkIdS, VisitorDTO dto) {
        
        AmusementPark park = af.getById(ps.parseStringId(parkIdS));
        Visitor v = vf.getById(dto.getId());

        if (v.getMoney() < park.getEntryFee()) {
            throw new IllegalRequestException("Cannot check in visitor because he/she cannot afford to pay the entry fee.");
        }
        if (v.getActive()) {
            throw new IllegalRequestException("Cannot check in visitor because he/she is already in an amusement park.");
        }

        v.setMoney(v.getMoney() - park.getEntryFee());
        v.setActive(true);
        
        v.getInAmusementPark().getVisitors().remove(v);
        
        ef.update(v.getInAmusementPark());
        
        v.setInAmusementPark(park);
        v.setStatus(Status.RESTING);
        park.getVisitors().add(v);

        ef.update(v);
        ef.update(park);

        return new VisitorDTO(v);
    }

    public VisitorDTO checkOut(String parkIdS, VisitorDTO dto) {
        AmusementPark park = af.getById(ps.parseStringId(parkIdS));

        Visitor v = vf.getById(dto.getId());
        if (!v.getInAmusementPark().equals(park)) {
            throw new IllegalRequestException("Cannot check out visitor because he/she is in an other amusement park.");
        }
        if(!v.getActive())
        {
            throw new IllegalRequestException("Cannot check out visitor because he/she has already checked out.");
        }
           
        v.setActive(false);
        ef.update(v);

        return new VisitorDTO(v);
    }
    
    public MessageDTO getNumberOfRestingVisitors(String parkIdS)
    {
        MessageDTO m = new MessageDTO();
        m.setDescription("Number of resting visitors");
        m.setMessage(af.getNumberOfRestingVisitorsByParkId(ps.parseStringId(parkIdS)).toString());
        
        return m;
    }
    
    public List<VisitorDTO> getVisitorsWithEnoughMoney(String parkIdS)
    {
        List<VisitorDTO> ret = new LinkedList<>();
        
        af.getVisitorsWithEnoughMoneyByParkId(ps.parseStringId(parkIdS)).stream().forEach( v -> ret.add(new VisitorDTO(v)));
        
        return ret;
    }
}
