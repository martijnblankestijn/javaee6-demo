package nl.ordina.scea.cigar.shop.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

@Named
public class EnumTypeI18NConverter implements Converter, Serializable {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        throw new IllegalArgumentException("Not expected.");
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }
        if (!Enum.class.isAssignableFrom(o.getClass())) {
            throw new IllegalArgumentException("Not an enum class");
        }
        String messageBundle = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle(messageBundle, locale);


        String className = o.getClass().getName();
        String key = className + '.' + ((Enum) o).name();
        return rb.getString(key);
    }

}