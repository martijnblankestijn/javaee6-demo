package nl.ordina.scea.cigar.shop.web.beans;

import nl.ordina.scea.cigar.shop.domainapi.Customer;

import javax.enterprise.inject.Model;
import javax.mail.internet.InternetAddress;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Model
public class CustomerFormBean implements Customer {
    @NotNull
    @Size(min = 2, max = 40)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 40)
    private String lastName;
    @NotNull
    @Size(min = 4, max = 100)
    private String addressLine;
    @NotNull
    @Size(min = 2, max = 40)
    private String city;
    @NotNull
    @Size(min = 2, max = 40)
    private String state;
    @NotNull
    @Size(min = 6, max = 7)
    private String zipCode;

    @NotNull
    private InternetAddress emailAddress;

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

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

    public InternetAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(InternetAddress emailAddress) {
        this.emailAddress = emailAddress;
    }
}
