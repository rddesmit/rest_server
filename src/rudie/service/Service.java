package rudie.service;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;
import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rudie (rddesmit@hotmail.com) on 18-7-14.
 */
@Path("/service")
public class Service {

    Connection c = null;

    public Service(){
        try {
            //maak connectie
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            //maak tabel
            c.prepareStatement("CREATE TABLE test_table (test_date VARCHAR(250))").execute();
            System.out.println("Table created");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("{datetime}")
    @Produces("application/json")
    public StreamingOutput getItems(@PathParam("datetime")String datetime){
        String databaseDatetime = null;

        try {
            //insert data
            PreparedStatement statement = c.prepareStatement("INSERT INTO test_table VALUES (?)");
            statement.setString(1, datetime);
            statement.execute();
            System.out.println("Data inserted");

            //select data
            statement = c.prepareStatement("SELECT * FROM test_table WHERE test_date = ?");
            statement.setString(1, datetime);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            databaseDatetime = resultSet.getString(1);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //item aanmaken
        HashMap<String, String> item = new HashMap<>();
        item.put("id", databaseDatetime);

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
