package nl.ordina.scea.cigar.shop.web.converter;

class EnumOrdinalKeyStrategy implements EnumKeyStrategy {
    @Override
    public String generateKey(Enum enumInstance) {
        return String.valueOf(enumInstance.ordinal());
    }

    @Override
    public Enum getInstance(Class<Enum> enumType, String value) {
        int intValue = Integer.parseInt(value);
        for (Enum instance : enumType.getEnumConstants()) {
            if (instance.ordinal() == intValue) {
                return instance;
            }
        }
        throw new IllegalArgumentException("No enum const class " + enumType.getClass().getName() + "." + value);
    }
}
