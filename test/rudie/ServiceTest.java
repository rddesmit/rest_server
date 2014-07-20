package rudie;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Rudie (rddesmit@hotmail.com) on 19-7-14.
 */
public class ServiceTest {

    /**
     * Controlle of list inderdaad goed verstuurd wordt
     * @throws IOException
     */
    @Test
    public void getItemsTest() throws IOException {
        Date datetime = new Date();

        Client client = ClientBuilder.newClient();
        String response = client.target("http://localhost:8080/rest/service/" + datetime.toString()).request().get(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> items = objectMapper.readValue(response, List.class);
        assertEquals(datetime.toString(), items.get(0).get("id"));
    }
}
