package nl.ordina.scea.cigar.shop.web.beans;

import nl.ordina.scea.cigar.shop.domainapi.CreditCard;
import nl.ordina.scea.cigar.shop.domainapi.CreditCardType;
import nl.ordina.scea.cigar.shop.validation.CreditCardExpiryMonth;
import nl.ordina.scea.cigar.shop.validation.CreditCardExpiryYear;
import nl.ordina.scea.cigar.shop.validation.CreditCardNumber;

import javax.enterprise.inject.Model;
import javax.validation.constraints.NotNull;

@Model
public class CreditCardFormBean implements CreditCard {
    @NotNull
    private CreditCardType type;

    @CreditCardNumber
    private Long number;

    @CreditCardExpiryMonth
    private Integer expirationMonth;

    @CreditCardExpiryYear
    private Integer expirationYear;

    public CreditCardType getType() {
        return type;
    }

    public void setType(CreditCardType type) {
        this.type = type;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }
}
