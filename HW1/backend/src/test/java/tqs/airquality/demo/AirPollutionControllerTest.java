package tqs.airquality.demo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tqs.airquality.demo.controllers.AirPollutionController;
import tqs.airquality.demo.models.CurrentAirPollution;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class AirPollutionControllerTest {
    @Mock
    private AirPollutionController airPollutionController;

    @InjectMocks
    private AirPollutionController airPollutionController1;

    @Test
    public void testGetAirPollution() {

        // Need to get the values from postman or other tool to test

        CurrentAirPollution currentAirPollution = new CurrentAirPollution();
        currentAirPollution.setCoord(new CurrentAirPollution.Coord(-8.659,40.6195 ));
        CurrentAirPollution.AirPollution airPollution = new CurrentAirPollution.AirPollution();
        CurrentAirPollution.Main main = new CurrentAirPollution.Main();
        main.setAqi(3);
        airPollution.setMain(main);
        // Write values here
        airPollution.setComponents(new CurrentAirPollution.Components(230.31, 0.38, 2.61, 120.16, 2.06, 7.44, 10.01, 2.85));
        airPollution.setDt(1680361136);
        currentAirPollution.setList(List.of(new CurrentAirPollution.AirPollution[]{airPollution}));


        // Uncomment this to test the values
        assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getCoord().getLon(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getCoord().getLon());
        // assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getList().get(0).getMain().getAqi(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getList().get(0).getMain().getAqi());
        // assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getList().get(0).getComponents().getCo(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getList().get(0).getComponents().getCo());
        // assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getList().get(0).getComponents().getNo(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getList().get(0).getComponents().getNo());
        // assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getList().get(0).getComponents().getNo2(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getList().get(0).getComponents().getNo2());
        // assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getList().get(0).getComponents().getO3(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getList().get(0).getComponents().getO3());
        // assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getList().get(0).getComponents().getSo2(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getList().get(0).getComponents().getSo2());
        // assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getList().get(0).getComponents().getPm2_5(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getList().get(0).getComponents().getPm2_5());
        // assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getList().get(0).getComponents().getPm10(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getList().get(0).getComponents().getPm10());
        // assertEquals(ResponseEntity.ok(currentAirPollution).getBody().getList().get(0).getComponents().getNh3(), airPollutionController1.getAirPollution(40.6195, -8.659).getBody().getList().get(0).getComponents().getNh3());


    }
}

