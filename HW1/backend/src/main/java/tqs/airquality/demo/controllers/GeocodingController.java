package tqs.airquality.demo.controllers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tqs.airquality.demo.models.Geocoding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class GeocodingController {

    private final RestTemplate restTemplate;

    public GeocodingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/geolocation")
    public ResponseEntity<List<Geocoding>> getGeolocation(@RequestParam String city, @RequestParam String countryCode) {
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + "," + countryCode + "&appid=a22bedb1e790d25274f1a0e16aaa7c8f";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // only keep the necessary fields
        List<Geocoding> geocodings = new ArrayList<>();
        ResponseEntity<List<Geocoding>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Geocoding>>(){});
        if (response.hasBody() && response.getBody() != null) {
            geocodings = response.getBody().stream()
                    .map(geocoding -> new Geocoding(geocoding.getName(), geocoding.getLat(), geocoding.getLon(), geocoding.getCountry()))
                    .collect(Collectors.toList());
        } else {
            // handle the case where the response body is null or empty
            System.err.println("The response body was null or empty");
        }

        return ResponseEntity.ok(geocodings);

    }
}

