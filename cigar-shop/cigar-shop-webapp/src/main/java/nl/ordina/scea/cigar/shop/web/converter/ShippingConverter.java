package nl.ordina.scea.cigar.shop.web.converter;

import nl.ordina.scea.cigar.shop.domainapi.Shipping;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;


@Named(value = "shippingConverter")
@ApplicationScoped
public class ShippingConverter implements Converter {
    @Inject
    private List<Shipping> shippingAlternatives;

    @Inject
    private Logger logger;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.out.println("Got: " + s);
        final Integer id = convertToKey(s);

        for (Shipping shippingAlternative : shippingAlternatives) {
            if (shippingAlternative.getId() == id) {
                logger.finest("Returning shipping alternative for id " + id + " " + shippingAlternative.getDescription());
                return shippingAlternative;
            }
        }

        logger.info("Shipping alternative not found for key " + s);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Unknown Shipping method", "The chosen shipping method is not recognized");
        throw new ConverterException(message);

    }

    /**
     * Converts the parameter to the key of Shipping
     *
     * @param value the value to convert
     * @return the key of the shipping instance.
     * @throws ConverterException in case of an invalid value
     */
    private Integer convertToKey(String value) throws ConverterException {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException nfe) {
            logger.info("Unexpected input, a number was expected, received is " + value);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Unknown shipping method", "The supplied shipping method requires an numeric shipping id.");
            throw new ConverterException(message);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        Shipping shipping = (Shipping) obj;
        return shipping.getId() + "";
    }
}
