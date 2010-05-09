package nl.ordina.scea.cigar.shop.web.factory;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import java.util.Locale;

public class I18NFactory {
    @Produces
    public Locale getLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }


}
