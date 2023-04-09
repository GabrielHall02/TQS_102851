package tqs.airquality.demo;

import org.junit.jupiter.api.Test;
import tqs.airquality.demo.models.Geocoding;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeocodingUnitTest {
    @Test
    public void testGettersAndSetters() {
        // Create a Geocoding object with some values
        Geocoding geo = new Geocoding("Lisbon", 38.7223, -9.1393, "Portugal");

        // Test the getters and setters for each field
        assertEquals("Lisbon", geo.getName());
        geo.setName("Porto");
        assertEquals("Porto", geo.getName());

        assertEquals(38.7223, geo.getLat());
        geo.setLat(40.6401);
        assertEquals(40.6401, geo.getLat());

        assertEquals(-9.1393, geo.getLon());
        geo.setLon(-8.6534);
        assertEquals(-8.6534, geo.getLon());

        assertEquals("Portugal", geo.getCountry());
        geo.setCountry("Spain");
        assertEquals("Spain", geo.getCountry());
    }
}
