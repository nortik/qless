package io.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.Location;
import io.swagger.repositories.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class LocationInit implements CommandLineRunner {

	@Autowired
	public LocationRepository locationRepository;

    @Override
    public void run(String... args) throws Exception {
        loadDB();
    }

    protected void loadDB() {
    	locationRepository.deleteAll();
        Location[] locations = new Location[]{};
        ObjectMapper mapper = new ObjectMapper();
        try {
        	ClassLoader classLoader = getClass().getClassLoader();
            locations = mapper.readValue(new File(classLoader.getResource("us-locator-etl.json").getFile()), Location[].class);
            Stream.of(locations).forEach(l -> locationRepository.save(l));
            System.out.println("Finished with the load of file (us-locator-etl) to the MongoDB");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
