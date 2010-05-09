package nl.ordina.scea.cigar.shop.web.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import java.io.Serializable;

@Named
public class EnumTypeConverter implements Converter, Serializable {
    private final EnumKeyStrategy keyStrategy = new EnumOrdinalKeyStrategy();


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if ("-1".equals(value)) {
            return null;
        }
        final ValueExpression valueExpression = uiComponent.getValueExpression("value");
        Class enumType = valueExpression.getType(facesContext.getELContext());
        return keyStrategy.getInstance(enumType, value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }
        if (!Enum.class.isAssignableFrom(o.getClass())) {
            throw new IllegalArgumentException("Not an enum class");
        }

        return keyStrategy.generateKey((Enum) o);
    }

}
