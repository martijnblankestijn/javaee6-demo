package nl.ordina.scea.cigar.shop.validation;

import nl.ordina.scea.cigar.shop.domainapi.CreditCardType;
import nl.ordina.scea.cigar.shop.web.beans.CreditCardFormBean;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Calendar;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreditCardNumberValidatorTest {
    private CreditCardFormBean creditCardForm;
    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        creditCardForm = new CreditCardFormBean();
        creditCardForm.setExpirationMonth(12);
        creditCardForm.setExpirationYear(Calendar.getInstance().get(Calendar.YEAR));
        creditCardForm.setNumber(1234123412341234L);
        creditCardForm.setType(CreditCardType.Mastercard);
    }

    @Test
    public void testCreditCardFormValid() {
        final Set<ConstraintViolation<CreditCardFormBean>> violations = validator.validate(creditCardForm);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testCreditCardNumber() {
        creditCardForm.setNumber(1L);
        final Set<ConstraintViolation<CreditCardFormBean>> violations = validator.validate(creditCardForm);
        assertEquals(1, violations.size());
    }

}
