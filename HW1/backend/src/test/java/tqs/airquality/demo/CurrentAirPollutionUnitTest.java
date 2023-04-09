package tqs.airquality.demo;

import org.junit.jupiter.api.Test;
import tqs.airquality.demo.models.CurrentAirPollution;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentAirPollutionUnitTest {

    @Test
    public void testGetSetCoord() {
        CurrentAirPollution currentAirPollution = new CurrentAirPollution();
        CurrentAirPollution.Coord coord = new CurrentAirPollution.Coord(10.0, 20.0);
        currentAirPollution.setCoord(coord);

        assertEquals(coord, currentAirPollution.getCoord());
    }

    @Test
    public void testGetSetList() {
        CurrentAirPollution currentAirPollution = new CurrentAirPollution();
        List<CurrentAirPollution.AirPollution> list = new ArrayList<>();
        list.add(new CurrentAirPollution.AirPollution(2, new CurrentAirPollution.Components(0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0), 123456789));
        currentAirPollution.setList(list);

        assertEquals(list, currentAirPollution.getList());
    }

    @Test
    public void testCoord() {
        CurrentAirPollution.Coord coord = new CurrentAirPollution.Coord(10.0, 20.0);

        assertEquals(10.0, coord.getLon(), 0.01);
        assertEquals(20.0, coord.getLat(), 0.01);

        coord.setLon(30.0);
        coord.setLat(40.0);

        assertEquals(30.0, coord.getLon(), 0.01);
        assertEquals(40.0, coord.getLat(), 0.01);
    }

    @Test
    public void testAirPollution() {
        CurrentAirPollution.AirPollution airPollution = new CurrentAirPollution.AirPollution(2, new CurrentAirPollution.Components(0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0), 123456789);

        assertEquals(2, airPollution.getMain().getAqi());
        assertEquals(0.3, airPollution.getComponents().getCo(), 0.01);
        assertEquals(0.4, airPollution.getComponents().getNo(), 0.01);
        assertEquals(0.5, airPollution.getComponents().getNo2(), 0.01);
        assertEquals(0.6, airPollution.getComponents().getO3(), 0.01);
        assertEquals(0.7, airPollution.getComponents().getSo2(), 0.01);
        assertEquals(0.8, airPollution.getComponents().getPm2_5(), 0.01);
        assertEquals(0.9, airPollution.getComponents().getPm10(), 0.01);
        assertEquals(1.0, airPollution.getComponents().getNh3(), 0.01);

        airPollution.setMain(new CurrentAirPollution.Main());
        airPollution.getMain().setAqi(3);
        airPollution.setComponents(new CurrentAirPollution.Components(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8));
    }
}

