import nl.ordina.scea.cigar.shop.UrlParser;
import nl.ordina.scea.cigar.shop.domainapi.ProductCategory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CigarParser {
    private Map<String, List<ParsedProduct>> manufacturers;

    public static void main(String[] args) {
        final CigarParser cigarParser = new CigarParser();
        try {
            cigarParser.parse();
            cigarParser.printSQL();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void printSQL() {
        int id = 0;
        int numberOfCategories = ProductCategory.values().length;
        for (Map.Entry<String, List<ParsedProduct>> manufacturer : manufacturers.entrySet()) {
            id++;
            System.out.println("insert into MANUFACTURER (ID, NAME) VALUES (" + id + ", '" + manufacturer.getKey() + "');");


            for (ParsedProduct product : manufacturer.getValue()) {
                BigDecimal price = new BigDecimal(product.getName().hashCode()).abs().divide(new BigDecimal(10)).remainder(new BigDecimal(100));

                StringBuilder insertProductQuery = new StringBuilder();
                insertProductQuery.append("insert into PRODUCT_DEFINITION (ID, CATEGORY, DESCRIPTION, NAME, PACKAGE, PRICE, CURRENCY_CODE, MANUFACTURER_ID) VALUES (");
                final String name = escape(product.getName());

                int category = name.substring(0, 1).hashCode() % numberOfCategories;

                insertProductQuery.append(product.getId())
                        .append(", ").append(category).append(", '")
                        .append(name)
                        .append("', '")
                        .append(name).append("', 'package', ").append(price.toString())
                        .append(", 'EUR',").append(id)
                        .append(");");
                System.out.println(insertProductQuery);

                StringBuilder insertInventoryQuery = new StringBuilder();
                insertInventoryQuery.append("insert into INVENTORY (PRODUCT_ID, INVENTORY) VALUES")
                        .append("( ").append(product.getId()).append(", ")
                        .append(20).append(");");
                System.out.println(insertInventoryQuery);
            }

        }


    }

    private String escape(String name) {
        return name.replaceAll("'", "''");
    }

    private void parse() throws XMLStreamException {
        final InputStream stream = this.getClass().getResourceAsStream("cigars.xml");
        final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        final XMLStreamReader streamReader = inputFactory.createXMLStreamReader(stream);
        manufacturers = new HashMap<String, List<ParsedProduct>>();

        int manufacturerId = 1;
        while (streamReader.hasNext()) {
            streamReader.next();
            final int type = streamReader.getEventType();
            if (XMLEvent.START_ELEMENT == type) {
                final QName qName = streamReader.getName();
                if ("a".equals(qName.getLocalPart())) {
                    UrlParser parser = new UrlParser(streamReader.getAttributeValue(0));
                    parser.parse();
                    if (parser.isValid()) {
                        final String manufacturer = parser.getManufacturer();
                        System.out.println("Id:" + parser.getId() + " , manufacture:" + manufacturer);

                        List<ParsedProduct> products = manufacturers.get(manufacturer);
                        if (products == null) {
                            products = new ArrayList<ParsedProduct>();
                            manufacturerId++;
                            manufacturers.put(parser.getManufacturer(), products);
                        }
                        final String text = streamReader.getElementText();
                        final ParsedProduct newProduct = new ParsedProduct(parser.getId(), text);
                        products.add(newProduct);
                        System.out.println(">>" + text);
                    }
                }
            }
        }
    }
}

class ParsedProduct {
    private final int id;
    private final String name;

    ParsedProduct(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
