/**
 *
 * @author Csaba Kadlecsik <kadlecsik@outlook.com>
 */
package csk.mobilewebshop.dto;

import csk.mobilewebshop.annotation.Validate;
import csk.mobilewebshop.constraint.DateOfBirthConstraint;
import csk.mobilewebshop.interceptor.BeanValidation;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validate
@DateOfBirthConstraint
public class UserDTO {

    @NotNull
    @Size(min = 3)
    private String userName;

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

    private LocalDate dateOfBirth;

    @NotNull
    private LocalDate registrationDate;

    private boolean admin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = passWord;
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

    public void setDateOfBirth(int year, int month, int day) {
        this.dateOfBirth = LocalDate.of(year, month, day);
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setRegistrationDate(int year, int month, int day) {
        this.registrationDate = LocalDate.of(year, month, day);
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
        hash = 79 * hash + Objects.hashCode(this.userName);
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
        return Objects.equals(this.userName, other.userName);
    }
}
