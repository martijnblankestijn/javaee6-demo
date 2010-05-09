package nl.ordina.scea.cigar.shop.web.converter;

class EnumNameKeyStrategy implements EnumKeyStrategy {
    @Override
    public String generateKey(Enum enumInstance) {
        return enumInstance.name();
    }

    @Override
    public Enum getInstance(Class<Enum> enumType, String value) {
        return Enum.valueOf(enumType, value);
    }
}
