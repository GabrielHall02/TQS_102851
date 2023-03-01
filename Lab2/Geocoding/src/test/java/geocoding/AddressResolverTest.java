package geocoding;

import connection.ISimpleHttpClient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {
    @Mock
    ISimpleHttpClient httpClient;

    @InjectMocks
    AddressResolver resolver;

    @Test
    void whenResolveDetiGps_returnJacintoMagalhaeAddress() throws ParseException, IOException, URISyntaxException {

        String json_res = "{\n" +
                "\t\"info\": {\n" +
                "\t\t\"statuscode\": 0,\n" +
                "\t\t\"copyright\": {\n" +
                "\t\t\t\"text\": \"© 2022 MapQuest, Inc.\",\n" +
                "\t\t\t\"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\",\n" +
                "\t\t\t\"imageAltText\": \"© 2022 MapQuest, Inc.\"\n" +
                "\t\t},\n" +
                "\t\t\"messages\": []\n" +
                "\t},\n" +
                "\t\"options\": {\n" +
                "\t\t\"maxResults\": 1,\n" +
                "\t\t\"ignoreLatLngInput\": false\n" +
                "\t},\n" +
                "\t\"results\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"providedLocation\": {\n" +
                "\t\t\t\t\"latLng\": {\n" +
                "\t\t\t\t\t\"lat\": 40.633116,\n" +
                "\t\t\t\t\t\"lng\": -8.658784\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"locations\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"street\": \"Avenida João Jacinto de Magalhães\",\n" +
                "\t\t\t\t\t\"adminArea6\": \"Aveiro\",\n" +
                "\t\t\t\t\t\"adminArea6Type\": \"Neighborhood\",\n" +
                "\t\t\t\t\t\"adminArea5\": \"Aveiro\",\n" +
                "\t\t\t\t\t\"adminArea5Type\": \"City\",\n" +
                "\t\t\t\t\t\"adminArea4\": \"Aveiro\",\n" +
                "\t\t\t\t\t\"adminArea4Type\": \"County\",\n" +
                "\t\t\t\t\t\"adminArea3\": \"\",\n" +
                "\t\t\t\t\t\"adminArea3Type\": \"State\",\n" +
                "\t\t\t\t\t\"adminArea1\": \"PT\",\n" +
                "\t\t\t\t\t\"adminArea1Type\": \"Country\",\n" +
                "\t\t\t\t\t\"postalCode\": \"3810-149\",\n" +
                "\t\t\t\t\t\"geocodeQualityCode\": \"B1AAA\",\n" +
                "\t\t\t\t\t\"geocodeQuality\": \"STREET\",\n" +
                "\t\t\t\t\t\"dragPoint\": false,\n" +
                "\t\t\t\t\t\"sideOfStreet\": \"L\",\n" +
                "\t\t\t\t\t\"linkId\": \"0\",\n" +
                "\t\t\t\t\t\"unknownInput\": \"\",\n" +
                "\t\t\t\t\t\"type\": \"s\",\n" +
                "\t\t\t\t\t\"latLng\": {\n" +
                "\t\t\t\t\t\t\"lat\": 40.63312,\n" +
                "\t\t\t\t\t\t\"lng\": -8.65873\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"displayLatLng\": {\n" +
                "\t\t\t\t\t\t\"lat\": 40.63312,\n" +
                "\t\t\t\t\t\t\"lng\": -8.65873\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"mapUrl\": \"\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        when(httpClient.doHttpGet(anyString())).thenReturn(json_res);

        Optional<Address> result = resolver.findAddressForLocation(40.633116,-8.658784);

        assertEquals( result.get(), new Address( "Avenida João Jacinto de Magalhães", "Aveiro", "", "3810-149", null) );

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        String json_res = "{\n" +
                "\t\"info\": {\n" +
                "\t\t\"statuscode\": 400,\n" +
                "\t\t\"copyright\": {\n" +
                "\t\t\t\"text\": \"© 2022 MapQuest, Inc.\",\n" +
                "\t\t\t\"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\",\n" +
                "\t\t\t\"imageAltText\": \"© 2022 MapQuest, Inc.\"\n" +
                "\t\t},\n" +
                "\t\t\"messages\": [\n" +
                "\t\t\t\"Illegal argument from request: Invalid LatLng specified.\"\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t\"options\": {\n" +
                "\t\t\"maxResults\": 1,\n" +
                "\t\t\"ignoreLatLngInput\": false\n" +
                "\t},\n" +
                "\t\"results\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"providedLocation\": {},\n" +
                "\t\t\t\"locations\": []\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        when(httpClient.doHttpGet(anyString())).thenReturn(json_res);

        Optional<Address> result = resolver.findAddressForLocation(-300, -810);

        assertThrows(NoSuchElementException.class, () -> result.get());
    }
}