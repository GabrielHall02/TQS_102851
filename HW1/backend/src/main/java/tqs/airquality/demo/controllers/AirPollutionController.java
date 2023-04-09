package tqs.airquality.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tqs.airquality.demo.models.CurrentAirPollution;

@RestController
public class AirPollutionController {

    private final RestTemplate restTemplate;


    public AirPollutionController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/air-pollution")
    public ResponseEntity<CurrentAirPollution> getAirPollution(@RequestParam double lat, @RequestParam double lon) {
        String url = "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat + "&lon=" + lon + "&appid=a22bedb1e790d25274f1a0e16aaa7c8f";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<CurrentAirPollution> response = restTemplate.exchange(url, HttpMethod.GET, entity, CurrentAirPollution.class);

        return response.ok(response.getBody());
    }
}


