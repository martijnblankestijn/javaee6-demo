package nl.ordina.cursus.test;

import nl.ordina.cursus.inventory.domain.v1.AvailabilityRequest;
import nl.ordina.cursus.inventory.domain.v1.AvailabilityResponse;
import nl.ordina.cursus.inventory.service.v1.InventoryService;
import nl.ordina.scea.inventory.service.AvailabilityService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(name = "InventoryService", serviceName = "InventoryService", portName = "InventoryServicePort",
        targetNamespace = "http://cursus.ordina.nl/inventory/service/v1")
@Named
public class InventoryServiceImpl implements InventoryService {
    private static final Logger LOG = Logger.getLogger(InventoryServiceImpl.class.getName());

    @Inject
    private AvailabilityService availabilityService;

    @Override
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAvailability", targetNamespace = "http://cursus.ordina.nl/inventory/domain/v1", className = "nl.ordina.cursus.inventory.domain.v1.GetAvailability")
    @ResponseWrapper(localName = "getAvailabilityResponse", targetNamespace = "http://cursus.ordina.nl/inventory/domain/v1", className = "nl.ordina.cursus.inventory.domain.v1.GetAvailabilityResponse")
    public AvailabilityResponse getAvailability(
            @WebParam(name = "availabilityRequest", targetNamespace = "")
            AvailabilityRequest availabilityRequest) {
        if (availabilityRequest == null || availabilityRequest.getProductId() < 0) {
            throw new IllegalArgumentException(" SHOULD NOT HAPPEN");
        }

        if (LOG.isLoggable(Level.FINEST)) {
            LOG.finest("Inventory request for product " + availabilityRequest.getProductId());
        }


        int available = availabilityService.availability(availabilityRequest.getProductId());

        AvailabilityResponse response = new AvailabilityResponse();
        response.setAvailable(available);
        response.setProductId(availabilityRequest.getProductId());

        if (LOG.isLoggable(Level.FINEST)) {
            LOG.finest("Availability " + response.getAvailable() + " for product " + response.getProductId());
        }
        return response;
    }
}
