package nl.ordina.scea.cigar.shop.web.converter;

interface EnumKeyStrategy {
    String generateKey(Enum enumInstance);

    Enum getInstance(Class<Enum> enumType, String value);
}
