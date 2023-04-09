package tqs.airquality.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tqs.airquality.demo.controllers.GeocodingController;
import tqs.airquality.demo.models.Geocoding;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeocodingTest {

    @Mock
    private GeocodingController geocodingController;

    @InjectMocks
    private GeocodingController geocodingController1;

    private static final String CITY = "Lisbon";
    private static final String COUNTRY_CODE = "PT";
    private static final String EXPECTED_NAME = "Lisbon";
    private static final double EXPECTED_LAT = 38.7077507;
    private static final double EXPECTED_LON = -9.1365919;
    private static final String EXPECTED_COUNTRY = "PT";

    @BeforeEach
    public void setup() {
        Geocoding geocoding = new Geocoding();
        geocoding.setName(EXPECTED_NAME);
        geocoding.setLat(EXPECTED_LAT);
        geocoding.setLon(EXPECTED_LON);
        geocoding.setCountry(EXPECTED_COUNTRY);

        // Unnecessary stubbings detected.
        // when(geocodingController.getGeolocation(CITY, COUNTRY_CODE)).thenReturn(ResponseEntity.ok(List.of(geocoding)));
    }

    @Test
    public void shouldReturnGeolocationForGivenCityAndCountryCode() {
        ResponseEntity<List<Geocoding>> response = geocodingController1.getGeolocation(CITY, COUNTRY_CODE);

        assertEquals(EXPECTED_NAME, response.getBody().get(0).getName());
        assertEquals(EXPECTED_LAT, response.getBody().get(0).getLat());
        assertEquals(EXPECTED_LON, response.getBody().get(0).getLon());
        assertEquals(EXPECTED_COUNTRY, response.getBody().get(0).getCountry());
    }

}

