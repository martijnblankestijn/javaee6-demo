package nl.ordina.scea.cigar.shop.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CreditCardExpiryYearValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface CreditCardExpiryYear {
    String message() default "{nl.ordina.scea.cigar.shop.validation.CreditCardExpiryYear.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
