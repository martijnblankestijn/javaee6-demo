package nl.ordina.scea.cigar.shop.access.external;

import nl.ordina.cursus.inventory.service.v1.InventoryService;
import nl.ordina.cursus.inventory.service.v1.InventorySystemService;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Session;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Utility class with all context information about resources.
 */
@ApplicationScoped
public class ResourceProvider {
    @Inject
    private transient Logger logger;

    @Resource(name = "mail/LocalMail")
    private Session mailSession;

    @WebServiceRef
    private InventorySystemService inventoryService;

    /**
     * Produces the mail session.
     *
     * @return the mail session
     */
    @Produces
    public Session getMailSession() {
        logger.fine("Retrieving the mail session");

        return mailSession;
    }


    @Produces
    public InventoryService getInventoryService(@Named("inventoryServiceEndpoint") final String endpoint) {
        logger.fine("Retrieving the web service InventoryService");

        InventoryService port = inventoryService.getInventorySystemPort();
        if (!BindingProvider.class.isAssignableFrom(port.getClass())) {
            throw new RuntimeException(port.getClass().getName() + " not assignable to " + BindingProvider.class.getName());
        }
        changeEndpoint(endpoint, port);

        return port;

    }

    private void changeEndpoint(String endpoint, InventoryService port) {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Changing the endpoint to " + endpoint);
        }
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
    }

    /**
     * @return the endpoint (url) of the inventory service.
     */
    @Produces
    @Named(value = "inventoryServiceEndpoint")
    public String getInventoryServiceEndpoint() {
        return "http://localhost:8080/inventory-service-provider/InventoryService?wsdl";
    }


}
