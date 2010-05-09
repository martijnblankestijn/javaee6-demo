package nl.ordina.scea.cigar.shop.accessapi;

import javax.mail.Address;

/**
 * Mailer.
 */
public interface Mailer {

    /**
     * Send a mail.
     *
     * @param to      the recipient
     * @param subject the subject of the message
     * @param text    the content of the message
     */
    void sendMail(Address to, String subject, String text);
}
