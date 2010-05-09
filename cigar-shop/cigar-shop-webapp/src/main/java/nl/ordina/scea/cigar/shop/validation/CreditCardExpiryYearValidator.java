package nl.ordina.scea.cigar.shop.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class CreditCardExpiryYearValidator implements ConstraintValidator<CreditCardExpiryYear, Integer> {

    @Override
    public void initialize(CreditCardExpiryYear creditCardExpiryYear) {
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext constraintValidatorContext) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year != null && year >= currentYear && year <= currentYear + 10;
    }
}
