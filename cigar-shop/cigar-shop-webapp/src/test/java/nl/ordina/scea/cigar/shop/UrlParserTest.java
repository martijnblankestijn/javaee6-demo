package nl.ordina.scea.cigar.shop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UrlParserTest {
    @Test
    public void simple() {

        String url = "viewcigar.asp?brand=734";
        UrlParser parser = new UrlParser(url);
        parser.parse();
        assertTrue(parser.isValid());
        assertEquals(734, parser.getId());
        assertEquals("viewcigar", parser.getManufacturer());
    }


}
