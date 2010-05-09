package nl.ordina.scea.cigar.shop.service;

import nl.ordina.scea.cigar.shop.accessapi.ShippingMethodSelector;
import nl.ordina.scea.cigar.shop.domainapi.ShippingMethod;

import javax.enterprise.util.AnnotationLiteral;

/**
 * Used to 'select' the ShippingService that supports a certain shipping method.
 */
class ShippingMethodQualifier extends AnnotationLiteral<ShippingMethodSelector> implements ShippingMethodSelector {
    private final ShippingMethod method;

    public ShippingMethodQualifier(final ShippingMethod method) {
        this.method = method;
    }

    @Override
    public ShippingMethod method() {
        return method;
    }
}
