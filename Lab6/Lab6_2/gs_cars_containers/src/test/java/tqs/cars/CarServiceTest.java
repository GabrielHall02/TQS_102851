package tqs.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import tqs.cars.model.Car;
import tqs.cars.data.CarRepository;
import tqs.cars.services.CarManagerService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
        Car car1 = new Car("Tesla", "S");
        Car car2 = new Car("Tesla", "3");
        Car car3 = new Car("Tesla", "X");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        Mockito.when(carRepository.findAll())
                .thenReturn(allCars);
        Mockito.when(carRepository.findByCarId(1L))
                .thenReturn(car1);
        Mockito.when(carRepository.findByCarId(2L))
                .thenReturn(car2);
        Mockito.when(carRepository.findByCarId(3L))
                .thenReturn(car3);
        Mockito.when(carRepository.findByCarId(4L))
                .thenThrow(NoSuchElementException.class);
    }

    @Test
    void whenSearchValidModel_thenCarShouldBeFound() {
        String model = "S";
        Car found = carManagerService.getCarDetails(1L).get();

        assertThat(found.getModel())
                .isEqualTo(model);
    }

    @Test
    void whenSearchInvalidModel_thenCarShouldNotBeFound() {
        String model = "S";
        Car found = carManagerService.getCarDetails(3L).get();

        assertThat(found.getModel())
                .isNotEqualTo(model);
    }

    @Test
    void whenSearchValidMaker_thenCarShouldBeFound() {
        String maker = "Tesla";
        Car found = carManagerService.getCarDetails(1L).get();

        assertThat(found.getMaker())
                .isEqualTo(maker);
    }

    @Test
    void whenSearchInvalidMaker_thenCarShouldNotBeFound() {
        String maker = "Audi";
        Car found = carManagerService.getCarDetails(3L).get();

        assertThat(found.getMaker())
                .isNotEqualTo(maker);
    }


    @Test
    void whenSearchAllCars_thenCarsShouldBeFound() {
        List<Car> found = carManagerService.getAllCars();

        assertThat(found)
                .isNotEmpty();
    }
}
