package nl.ordina.scea.cigar.shop.accessapi;

import nl.ordina.scea.cigar.shop.domainapi.ShippingMethod;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Shipping method selector.
 */
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface ShippingMethodSelector {
    /**
     * The supported shipping method.
     *
     * @return the shipping method
     */
    public ShippingMethod method();
}
