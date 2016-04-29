package csk.amusementpark.dto;

import csk.amusementpark.annotation.Validate;
import csk.amusementpark.entity.amusementpark.Address;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validate
public class AddressDTO {
    
    @NotNull
    @Size(min = 1)
    private String zipCode;
    
    @NotNull
    @Size(min = 1)
    private String country;
    
    @NotNull
    @Size(min = 1)
    private String city;
    
    @NotNull
    @Size(min = 1)
    private String street;
    
    @NotNull
    @Size(min = 1)
    private String number;

    public AddressDTO() {
        //Paraméter nélküli konstruktor
    }

    public AddressDTO(String zipCode, String country, String city, String street, String number) {
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public AddressDTO(Address address) {
        this(address.getZipCode(), address.getCountry(), address.getCity(), address.getStreet(), address.getNumber());
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
