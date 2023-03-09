package tqs.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqs.cars.data.CarRepository;
import tqs.cars.model.Car;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindByCarId_thenReturnCar() {
        // given
        Car tesla = new Car("Tesla", "S");
        entityManager.persistAndFlush(tesla);
        // when
        Car found = carRepository.findByCarId(tesla.getCarId());

        // then
        assertThat(found.getCarId()).isEqualTo(tesla.getCarId());
    }

    @Test
    void whenFindByMaker_thenReturnCar() {
        // given
        Car tesla = new Car("Tesla", "S");
        entityManager.persistAndFlush(tesla);

        // when
        Car found = carRepository.findByMaker(tesla.getMaker());

        // then
        assertThat(found.getMaker()).isEqualTo(tesla.getMaker());
    }

    @Test
    void whenFindByModel_thenReturnCar() {
        // given
        Car tesla = new Car("Tesla", "S");
        entityManager.persist(tesla);
        entityManager.flush();

        // when
        Car found = carRepository.findByModel(tesla.getModel());

        // then
        assertThat(found.getModel()).isEqualTo(tesla.getModel());
    }

    @Test
    void whenFindAll_thenReturnAllCars() {
        // given
        Car tesla = new Car("Tesla", "S");
        Car bmw = new Car("BMW", "X5");
        entityManager.persist(tesla);
        entityManager.persist(bmw);
        entityManager.flush();

        // when
        List<Car> found = carRepository.findAll();

        // then
        assertThat(found).hasSize(2).extracting(Car::getMaker).containsOnly(tesla.getMaker(), bmw.getMaker());
    }

    @Test
    void whenInvalidMaker_thenReturnNull() {
        // given
        Car tesla = new Car("Tesla", "S");
        entityManager.persist(tesla);
        entityManager.flush();

        // when
        Car found = carRepository.findByMaker("BMW");

        // then
        assertThat(found).isNull();
    }

    @Test
    void whenInvalidModel_thenReturnNull() {
        // given
        Car tesla = new Car("Tesla", "S");
        entityManager.persist(tesla);
        entityManager.flush();

        // when
        Car found = carRepository.findByModel("X5");

        // then
        assertThat(found).isNull();
    }
}
