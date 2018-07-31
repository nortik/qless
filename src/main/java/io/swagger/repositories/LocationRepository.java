package io.swagger.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.swagger.model.Location;

public interface LocationRepository extends CrudRepository<Location, String> {

	@Query("{'source.globalId' :?0}")
	Location findByGlobalId(String locationGid);

}
