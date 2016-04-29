package csk.amusementpark.entity.amusementpark;

import csk.amusementpark.dto.AmusementParkDTO;
import csk.amusementpark.entity.machine.Machine;
import csk.amusementpark.entity.visitor.Visitor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AMUSEMENT_PARK")
@NamedQueries({
    @NamedQuery(name = "getAllParks", query = "SELECT a FROM AmusementPark a"),
    @NamedQuery(name = "getParkById", query = "SELECT a FROM AmusementPark a WHERE a.id = :id")
})
public class AmusementPark implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Embedded
    private Address address;
    
    private Integer entryFee;

    @Column(name = "FINANCIAL_CAPITAL")
    private Integer financialCapital;

    @Column(name = "TOTAL_AREA")
    private Integer totalArea;

    @OneToMany(mappedBy = "amusementPark", orphanRemoval = true)
    private final List<Machine> machines = new ArrayList<>();

    @OneToMany(mappedBy = "inAmusementPark")
    private final List<Visitor> visitors = new ArrayList<>();

    @OneToMany(mappedBy = "amusementPark")
    private final List<GuestBookEntry> guestBookEntries = new ArrayList<>();

    public AmusementPark() {
        //Paraméter nélküli konstruktor
    }

    public AmusementPark(String name, Address address, Integer entryFee, Integer financialCapital, Integer totalArea) {
        this.name = name;
        this.address = address;
        this.entryFee = entryFee;
        this.financialCapital = financialCapital;
        this.totalArea = totalArea;
    }

    public AmusementPark(Integer id, String name, Address address, Integer entryFee, Integer financialCapital, Integer totalArea) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.entryFee = entryFee;
        this.financialCapital = financialCapital;
        this.totalArea = totalArea;
    }

    public AmusementPark(AmusementParkDTO dto) {
        this(dto.getName(), new Address(dto.getAddress()),dto.getEntryFee(),dto.getFinancialCapital(), dto.getTotalArea());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getFinancialCapital() {
        return financialCapital;
    }

    public void setFinancialCapital(Integer financialCapital) {
        this.financialCapital = financialCapital;
    }

    public Integer getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public List<GuestBookEntry> getGuestBookEntries() {
        return guestBookEntries;
    }

    public Integer getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(Integer entryFee) {
        this.entryFee = entryFee;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AmusementPark other = (AmusementPark) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
