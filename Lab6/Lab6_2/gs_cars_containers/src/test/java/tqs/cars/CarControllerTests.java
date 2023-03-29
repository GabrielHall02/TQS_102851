package tqs.cars;

import org.springframework.http.MediaType;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import tqs.cars.model.Car;
import tqs.cars.boundary.CarController;
import tqs.cars.services.CarManagerService;

@WebMvcTest(CarController.class)
public class CarControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    private Car demoCar;

    @BeforeEach
    public void setUp() throws Exception {
        demoCar = new Car("Tesla", "S");
    }

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        when(service.save(any(Car.class))).thenReturn(demoCar);

        mvc.perform(post("/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(demoCar)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is(demoCar.getMaker())))
                .andExpect(jsonPath("$.model", is(demoCar.getModel())));

        verify(service, VerificationModeFactory.times(1)).save(any(Car.class));
        reset(service);
    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car car1 = new Car("Tesla", "S");
        Car car2 = new Car("Tesla", "3");
        Car car3 = new Car("Tesla", "X");

        List<Car> allCars= Arrays.asList(car1, car2, car3);

        when(service.getAllCars()).thenReturn(allCars);

        when(service.getCarDetails(any(Long.class))).thenReturn(Optional.of(demoCar));

        mvc.perform(get("/api/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker", is(demoCar.getMaker())))
                .andExpect(jsonPath("$.model", is(demoCar.getModel())));

        verify(service, VerificationModeFactory.times(1)).getCarDetails(any(Long.class));
        reset(service);
    }

}
