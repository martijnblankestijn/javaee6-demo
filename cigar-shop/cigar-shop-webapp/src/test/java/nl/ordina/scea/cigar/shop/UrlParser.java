package nl.ordina.scea.cigar.shop;

public class UrlParser {
    private final String url;
    private int id;
    private String manufacturer;
    private boolean valid;
    private static final String BRAND = "brand=";

    public UrlParser(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void parse() {
        final int positionPoint = url.indexOf('.');
        if (positionPoint < 0) {
            valid = false;
            return;
        }
        manufacturer = url.substring(0, positionPoint);

        final int startOfId = url.indexOf(BRAND);
        if (startOfId < 0) {
            valid = false;
            return;
        }
        id = Integer.parseInt(url.substring(startOfId + BRAND.length(), url.length()));
        valid = true;
    }

    public boolean isValid() {
        return valid;
    }
}
