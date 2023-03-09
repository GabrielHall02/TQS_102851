package tqs.cars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import tqs.cars.model.Car;
import tqs.cars.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarRestControllerIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateCar() {
        Car tesla = new Car("Tesla", "S");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", tesla, Car.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("Tesla");
    }

    @Test
    void givenCars_whenGetCars_thenStatus200() {
        Car tesla = new Car("Tesla", "S");
        Car bmw = new Car("BMW", "i3");
        repository.saveAndFlush(tesla);
        repository.saveAndFlush(bmw);

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsOnly("Tesla", "BMW");
    }


}
