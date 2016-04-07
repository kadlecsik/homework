package csk.mobilewebshop.dto;

import csk.mobilewebshop.annotation.Validate;
import csk.mobilewebshop.interceptor.BeanValidation;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Csaba Kadlecsik <kadlecsik@outlook.com>
 */
@Validate
public class MobileDTO {

    @NotNull
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String id;

    @Min(1)
    private Integer price;

    @Min(0)
    private Integer piece;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    @NotNull
    @Size(min = 3)
    private String type;

    @NotNull
    @Size(min = 3)
    private String manufacturer;

    public String getType() {
        return type;
    }

    public MobileDTO() {
    }
    
    
    
    public MobileDTO(String type, String manufacturer, String id, Integer price, Integer piece) {
        this.id = id;
        this.price = price;
        this.piece = piece;
        this.type = type;
        this.manufacturer = manufacturer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final MobileDTO other = (MobileDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
