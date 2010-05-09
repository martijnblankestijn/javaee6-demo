package nl.ordina.scea.cigar.shop.domainapi;

import javax.mail.internet.InternetAddress;

public interface Customer {
    String getName();

    String getLastName();

    InternetAddress getEmailAddress();

    String getFirstName();

    String getZipCode();

    String getAddressLine();

    String getCity();

    String getState();
}
