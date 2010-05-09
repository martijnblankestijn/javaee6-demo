package nl.ordina.scea.cigar.shop.validation;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreditCardExpiryYearValidatorTest {
    @Test
    public void testIsValid() throws Exception {
        CreditCardExpiryYearValidator validator = new CreditCardExpiryYearValidator();
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        assertFalse(validator.isValid(currentYear - 1, null));
        assertTrue(validator.isValid(currentYear, null));
        assertTrue(validator.isValid(currentYear + 10, null));
        assertFalse(validator.isValid(currentYear + 11, null));
    }
}
