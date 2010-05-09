package nl.ordina.scea.cigar.shop.access.external;

import nl.ordina.scea.cigar.shop.accessapi.Mailer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mailer based on STMP.
 */
@RequestScoped
public class SmtpMailer implements Mailer {
    @Inject
    private transient Logger logger;
    @Inject
    private Session mailSession;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMail(Address to, String subject, String text) {
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Sending mail to " + to + " about " + subject);
        }
        Message message = new MimeMessage(mailSession);
        try {
            message.setSubject(subject);
            message.setText(text);
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
