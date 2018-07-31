package io.swagger.api;

import io.swagger.model.APIError;
import java.math.BigDecimal;

import io.swagger.model.Location;
import io.swagger.repositories.LocationRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-29T08:10:59.063Z")

@Controller
public class MerchantApiController implements MerchantApi {    
    	
	@Autowired
    public LocationRepository locationRepository;

	@SuppressWarnings("unused")
	public ResponseEntity<?> merchantLocationGet(
    		@ApiParam(value = "",required=true) @PathVariable("location_gid") String locationGid,
    		
			@ApiParam(value = "If set, limits result to locations having/restricting mobile access.")
    		@RequestParam(value = "mobileClientAccess", required = false) Boolean mobileClientAccess,
    		
			@ApiParam(value = "If true, omit the merchant information from returned locations.")
    		@RequestParam(value = "omitMerchantInfo", required = false) Boolean omitMerchantInfo,
    		
			@ApiParam(value = "If true, omit the contact information from returned locations.")
    		@RequestParam(value = "omitContactInfo", required = false) Boolean omitContactInfo,
    		
			@ApiParam(value = "If true, omit the list of supported consumer features from the returned locations.")
    		@RequestParam(value = "omitConsumerFeatures", required = false) Boolean omitConsumerFeatures,
    		
			@ApiParam(value = "An optional fields mask." )
    		@RequestHeader(value="X-Fields", required=false) String xFields) {

		if(locationGid == null){
			return new ResponseEntity<APIError>(new APIError(), HttpStatus.BAD_REQUEST);
		}

		Location location = locationRepository.findByGlobalId(locationGid);
		
		if(location == null){
			return new ResponseEntity<APIError>(new APIError(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Location>(location, HttpStatus.OK);

	}

    public ResponseEntity<?> merchantLocationSearch(
    		@ApiParam(value = "Identify and sort merchants or locations for which the search text offers approximate matches.")
    		@RequestParam(value = "searchText", required = false) String searchText,
			
    		@ApiParam(value = "Limits results to a region with its center specified by the given GPS longitude.")
    		@RequestParam(value = "longitude", required = false) BigDecimal longitude,
			
    		@ApiParam(value = "Limits results to a region with its center specified by the given GPS latitude.")
    		@RequestParam(value = "latitude", required = false) BigDecimal latitude,
			
    		@ApiParam(value = "Radius of the search area in meters.", defaultValue = "50000")
    		@Valid @RequestParam(value = "searchRadius", required = false, defaultValue="50000") Integer searchRadius,
			
    		@ApiParam(value = "Limits/queries locations by the provided global identifier.")
    		@Valid @RequestParam(value = "gid", required = false) List<String> gid,
			
    		@ApiParam(value = "The maximum number of locations to return for a single query.", defaultValue = "10")
    		@RequestParam(value = "maximumResults", required = false, defaultValue="10") Integer maximumResults,
			
    		@ApiParam(value = "If set, limits results to locations having/restricting mobile access.")
    		@RequestParam(value = "mobileClientAccess", required = false) Boolean mobileClientAccess,
    		
			@ApiParam(value = "If true, omit the merchant information from returned locations.")
    		@RequestParam(value = "omitMerchantInfo", required = false) Boolean omitMerchantInfo,
    		
			@ApiParam(value = "If true, omit the contact information from returned locations.")
    		@RequestParam(value = "omitContactInfo", required = false) Boolean omitContactInfo,
    		
			@ApiParam(value = "If true, omit the list of supported consumer features from the returned locations.")
    		@RequestParam(value = "omitConsumerFeatures", required = false) Boolean omitConsumerFeatures,
    		
			@ApiParam(value = "An optional fields mask" ) 
    		@RequestHeader(value="X-Fields", required=false) String xFields) {

		if (maximumResults < 0){
			return new ResponseEntity<APIError>(new APIError(), HttpStatus.BAD_REQUEST);
		}

		List<Location> locations = (List<Location>) locationRepository.findAll();
		
		if(locations.isEmpty()){
			return new ResponseEntity<APIError>(new APIError(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }

}
