package nl.ordina.scea.cigar.shop.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Min(value = 1)
@Max(value = 12)
@NotNull
@ReportAsSingleViolation
public @interface CreditCardExpiryMonth {
    String message() default "{nl.ordina.scea.cigar.shop.validation.CreditCardExpiryMonth.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
