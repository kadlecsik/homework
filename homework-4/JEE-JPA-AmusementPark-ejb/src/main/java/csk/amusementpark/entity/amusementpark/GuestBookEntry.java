package csk.amusementpark.entity.amusementpark;

import csk.amusementpark.dto.GuestBookEntryDTO;
import csk.amusementpark.entity.visitor.Visitor;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GUESTBOOK_ENTRY")
@NamedQuery(name = "getEntryById", query = "SELECT e FROM GuestBookEntry e WHERE e.id = :id")
public class GuestBookEntry implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne
    private AmusementPark amusementPark;

    @ManyToOne
    private Visitor visitor;

    @Temporal(TemporalType.DATE)
    @Column(name = "ENTRY_DATE")
    private Date dateOfEntry;

    @Column(name = "ENTRY_TEXT")
    private String textEntry;
    
    public GuestBookEntry() {
        //Paraméter nélküli konstruktor
    }

    public GuestBookEntry(AmusementPark amusementPark, Visitor visitor, Date dateOfEntry, String textEntry) {
        this.amusementPark = amusementPark;
        this.visitor = visitor;
        this.dateOfEntry = dateOfEntry;
        this.textEntry = textEntry;
    }

    public GuestBookEntry(Integer id, AmusementPark amusementPark, Visitor visitor, Date dateOfEntry, String textEntry) {
        this.id = id;
        this.amusementPark = amusementPark;
        this.visitor = visitor;
        this.dateOfEntry = dateOfEntry;
        this.textEntry = textEntry;
    }
    
    public GuestBookEntry(GuestBookEntryDTO dto) {
        this.dateOfEntry = dto.getDateOfEntry();
        this.textEntry = dto.getTextEntry();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AmusementPark getAmusementPark() {
        return amusementPark;
    }

    public void setAmusementPark(AmusementPark amusementPark) {
        this.amusementPark = amusementPark;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getTextEntry() {
        return textEntry;
    }

    public void setTextEntry(String textEntry) {
        this.textEntry = textEntry;
    }
    
}
