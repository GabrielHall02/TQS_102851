package tqs.airquality.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tqs.airquality.demo.models.CurrentAirPollution;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirPollutionControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAirPollution() {
        // Given
        double lat = 40.6195;
        double lon = -8.659;

        // When
        ResponseEntity<CurrentAirPollution> response = restTemplate.getForEntity("/air-pollution?lat={lat}&lon={lon}", CurrentAirPollution.class, lat, lon);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getList());
        assertFalse(response.getBody().getList().isEmpty());
        assertNotNull(response.getBody().getList().get(0).getComponents());
        assertNotNull(response.getBody().getList().get(0).getMain());
    }

}
