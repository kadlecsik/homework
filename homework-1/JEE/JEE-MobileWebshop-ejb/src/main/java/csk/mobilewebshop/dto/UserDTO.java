package csk.mobilewebshop.dto;

import csk.mobilewebshop.adapter.LocalDateAdapter;
import csk.mobilewebshop.annotation.Validate;
import csk.mobilewebshop.constraint.DateOfBirthConstraint;
import csk.mobilewebshop.constraint.PastConstraint;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Validate
@DateOfBirthConstraint
public class UserDTO {

    @NotNull
    @Size(min = 3)
    private String username;

    @NotNull
    @Size(min = 6)
    @Pattern.List({
        @Pattern(regexp = ".*[a-z].*"),
        @Pattern(regexp = ".*[A-Z].*"),
        @Pattern(regexp = ".*[0-9].*"),
        @Pattern(regexp = ".*[=+<>\\.,].*")
    })
    private String password;

    private String firstName;
    private String lastName;
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @PastConstraint
    private LocalDate dateOfBirth;
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull
    private LocalDate registrationDate;

    private boolean admin;

    public UserDTO(String userName, String password, String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate, boolean admin) {
        this.username = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.admin = admin;
    }

    public UserDTO(String userName, String password, LocalDate registrationDate) {
        this.username = userName;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public UserDTO() {
        //Default construction
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.username);
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
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

}
