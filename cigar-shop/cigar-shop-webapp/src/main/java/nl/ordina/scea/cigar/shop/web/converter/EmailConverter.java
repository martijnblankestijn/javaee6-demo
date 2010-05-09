package nl.ordina.scea.cigar.shop.web.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@Named(value = "emailConverter")
@RequestScoped
public class EmailConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        try {
            return new InternetAddress(s);
        } catch (AddressException nfe) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid email address", "The supplied text is not a valid email address.");
            throw new ConverterException(message);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        System.out.println("Object received was: " + obj + " Type: " + obj.getClass().getName());
        if (!(obj instanceof Address)) {
            throw new IllegalArgumentException("Not an address but a " + obj.getClass().getName());
        }
        return obj.toString();
    }
}