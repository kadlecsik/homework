package csk.amusementpark.entity.amusementpark;

import csk.amusementpark.dto.AddressDTO;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

    private String zipCode;

    private String country;

    private String city;

    private String street;

    private String number;

    public Address() {
        //Paraméter nélküli konstruktor
    }

    public Address(String zipCode, String country, String city, String street, String number) {
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Address(AddressDTO dto) {
        this(dto.getZipCode(), dto.getCountry(), dto.getCity(), dto.getStreet(), dto.getNumber());
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
