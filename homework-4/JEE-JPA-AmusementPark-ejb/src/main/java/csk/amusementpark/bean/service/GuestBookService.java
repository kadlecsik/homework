package csk.amusementpark.bean.service;

import csk.amusementpark.bean.facade.AmusementParkFacade;
import csk.amusementpark.bean.facade.EntityFacade;
import csk.amusementpark.bean.facade.GuestBookEntryFacade;
import csk.amusementpark.bean.facade.VisitorFacade;
import csk.amusementpark.dto.GuestBookEntryDTO;
import csk.amusementpark.entity.amusementpark.AmusementPark;
import csk.amusementpark.entity.amusementpark.GuestBookEntry;
import csk.amusementpark.entity.visitor.Visitor;
import csk.amusementpark.exception.IllegalRequestException;
import csk.amusementpark.interceptor.ValidatorInterceptor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
@LocalBean
public class GuestBookService {

    @Inject
    private EntityFacade ef;

    @Inject
    private AmusementParkFacade af;

    @Inject
    private VisitorFacade vf;

    @Inject
    private GuestBookEntryFacade gf;

    @Inject
    private ParserService ps;
    
    @Interceptors(ValidatorInterceptor.class)
    public GuestBookEntryDTO addBookEntry(String parkIdS, String visitorIdS, GuestBookEntryDTO dto) {
        Visitor v = vf.getById(ps.parseStringId(visitorIdS));

        if (!v.getInAmusementPark().getId().equals(ps.parseStringId(parkIdS)) || !v.getActive()) {
            throw new IllegalRequestException("Can not add this entry, because the visitor is not in the park.");
        }

        AmusementPark a = af.getById(ps.parseStringId(parkIdS));

        GuestBookEntry e = new GuestBookEntry(dto);

        e.setAmusementPark(a);
        e.setVisitor(v);
        a.getGuestBookEntries().add(e);
        
        ef.create(e);
        ef.update(a);
        
        return new GuestBookEntryDTO(e);
    }

    public List<GuestBookEntryDTO> getBookEntriesForOneUser(String parkIdS, String visitorIdS) {
        List<GuestBookEntryDTO> ret = new ArrayList<>();
        gf.getByUserIdAndParkId(ps.parseStringId(visitorIdS), ps.parseStringId(parkIdS)).stream().forEach(g -> ret.add(new GuestBookEntryDTO(g)));
        return ret;
    }

    public List<GuestBookEntryDTO> getBookEntriesForPark(String parkIdS) {
        List<GuestBookEntryDTO> ret = new ArrayList<>();
        gf.getByParkId(ps.parseStringId(parkIdS)).stream().forEach(g -> ret.add(new GuestBookEntryDTO(g)));
        return ret;
    }

    public GuestBookEntryDTO getBookEntry(String entryIdS) {
        return new GuestBookEntryDTO(gf.getById(ps.parseStringId(entryIdS)));
    }

    public GuestBookEntryDTO updateBookEntry(String entryIdS, GuestBookEntryDTO dto) {
        GuestBookEntry g = gf.getById(ps.parseStringId(entryIdS));

        if (dto.getVisitorId() != null && !g.getVisitor().getId().equals(dto.getVisitorId())) {
            throw new IllegalRequestException("The visitor cannot be changed this way. Delete the entry and create it again.");
        }

        g.setDateOfEntry(dto.getDateOfEntry() == null ? g.getDateOfEntry() : dto.getDateOfEntry());
        g.setTextEntry(dto.getTextEntry() == null ? g.getTextEntry() : dto.getTextEntry());

        ef.update(g);

        return new GuestBookEntryDTO(g);
    }

    public GuestBookEntryDTO deleteBookEntry(String entryIdS) {
        return new GuestBookEntryDTO(ef.delete(gf.getById(ps.parseStringId(entryIdS))));
    }
}
