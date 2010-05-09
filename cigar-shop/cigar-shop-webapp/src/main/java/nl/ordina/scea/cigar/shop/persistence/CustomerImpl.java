package nl.ordina.scea.cigar.shop.persistence;

import nl.ordina.scea.cigar.shop.domainapi.Customer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class CustomerImpl implements Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "ADDRESS_LINE")
    private String addressLine;
    private String city;
    private String state;
    @Column(name = "ZIP_CODE")
    private String zipCode;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    protected CustomerImpl() {
    }

    public CustomerImpl(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public InternetAddress getEmailAddress() {
        try {
            return new InternetAddress(emailAddress);
        } catch (AddressException e) {
            throw new RuntimeException("Exception while accessing database value " + emailAddress);
        }
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

    public void setEmailAddress(InternetAddress emailAddress) {
        this.emailAddress = emailAddress.toString();
    }
}
