package nl.ordina.scea.cigar.shop.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@ApplicationScoped
@Named("i18n")
public class MessageResolver {
    private String messageBundle;


    @PostConstruct
    public void init() {
        messageBundle = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
    }

    public List<SelectItem> getItemsFor(Locale locale, Enum[] enumValues) {
        if (enumValues == null) {
            throw new IllegalArgumentException();
        }
        List<SelectItem> items = new ArrayList<SelectItem>(enumValues.length);

        for (Enum anEnum : enumValues) {
            final String label = translate(anEnum, locale);
            items.add(new SelectItem(anEnum, label));

        }
        return items;
    }

    private String translate(Enum enumInstance, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(messageBundle, locale);
        String className = enumInstance.getClass().getName();
        String key = className + '.' + enumInstance.name();
        return resourceBundle.getString(key);
    }
}
