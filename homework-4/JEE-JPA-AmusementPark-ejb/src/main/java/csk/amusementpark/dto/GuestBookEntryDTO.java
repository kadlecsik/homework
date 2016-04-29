package csk.amusementpark.dto;

import csk.amusementpark.adapter.DateAdapter;
import csk.amusementpark.annotation.Validate;
import csk.amusementpark.entity.amusementpark.GuestBookEntry;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Validate
public class GuestBookEntryDTO {

    private Integer id;
    
    private Integer visitorId;
    
    @NotNull
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dateOfEntry;
    
    @NotNull
    @Size(min = 1)
    private String textEntry;

    public GuestBookEntryDTO() {
        //Paraméter nélküli konstruktor
    }

    public GuestBookEntryDTO(Date dateOfEntry, String textEntry) {
        this.dateOfEntry = dateOfEntry;
        this.textEntry = textEntry;
    }

    public GuestBookEntryDTO(GuestBookEntry entry) {
        this(entry.getDateOfEntry(), entry.getTextEntry());
        this.visitorId = entry.getVisitor().getId();
        this.id = entry.getId();
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

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
