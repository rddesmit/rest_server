package rudie.application;

import rudie.service.Service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rudie (rddesmit@hotmail.com) on 18-7-14.
 */
@ApplicationPath("/rest")
public class RestApplication extends Application {

    //aangeven welke services er zijn
    public Set<Class<?>> getClasses(){
        Set<Class<?>> classes = new HashSet<>();
        classes.add(Service.class);
        return classes;
    }

    public Set<Object> getSingletons(){
        return Collections.emptySet();
    }
}
