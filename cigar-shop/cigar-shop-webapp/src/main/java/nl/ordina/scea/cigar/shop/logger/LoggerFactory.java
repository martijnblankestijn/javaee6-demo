package nl.ordina.scea.cigar.shop.logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * Factory for logging.
 * 'Just because it can.
 */
public class LoggerFactory {
    /**
     * Produces a logger for the caller identified by its fully qualified name.
     *
     * @param injectionPoint the point to which the logger will be injected
     * @return the logger
     */
    @Produces
    public Logger getLogger(InjectionPoint injectionPoint) {
        final String loggerName = injectionPoint.getMember().getDeclaringClass().getName();
        System.out.println("Creating logger for " + loggerName);
        return Logger.getLogger(loggerName);
    }

}
