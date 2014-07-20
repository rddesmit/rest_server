package rudie.service;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rudie (rddesmit@hotmail.com) on 18-7-14.
 */
@Path("/service")
public class Service {

    @GET
    @Path("{datetime}")
    @Produces("application/json")
    public StreamingOutput getItems(@PathParam("datetime")String datetime){
        //item aanmaken
        HashMap<String, String> item = new HashMap<>();
        item.put("id", datetime.toString());

        //item in items list stoppen
        List<HashMap<String, String>> items = new ArrayList<>();
        items.add(item);

        //mbv lambda omzetten naar json en versturen
        return outputStream -> {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(outputStream, items);
        };
    }
}
