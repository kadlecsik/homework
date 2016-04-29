package csk.amusementpark.dto;

import csk.amusementpark.annotation.Validate;
import csk.amusementpark.entity.amusementpark.AmusementPark;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validate
public class AmusementParkDTO {
    
    private Integer id;
    
    @NotNull
    @Size(min = 1)
    private String name;
    
    @NotNull
    private AddressDTO address;
    
    @NotNull
    @Min(1)
    private Integer financialCapital;
    
    @NotNull
    @Min(1)
    private Integer totalArea;
    
    @NotNull
    @Min(1)
    private Integer entryFee;
    
    public AmusementParkDTO() {
        //Paraméter nélküli konstruktor
    }

    public AmusementParkDTO(String name, AddressDTO address, Integer financialCapital, Integer totalArea, Integer entryFee) {
        this.name = name;
        this.address = address;
        this.financialCapital = financialCapital;
        this.totalArea = totalArea;
        this.entryFee = entryFee;
    }

    public AmusementParkDTO(Integer id, String name, AddressDTO address, Integer financialCapital, Integer totalArea, Integer entryFee) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.financialCapital = financialCapital;
        this.totalArea = totalArea;
        this.entryFee = entryFee;
    }

    public AmusementParkDTO(AmusementPark amusementPark) {
        this(amusementPark.getId(), amusementPark.getName(), new AddressDTO(amusementPark.getAddress()), amusementPark.getFinancialCapital(), amusementPark.getTotalArea(), amusementPark.getEntryFee());
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
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

    public Integer getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(Integer entryFee) {
        this.entryFee = entryFee;
    }

}
