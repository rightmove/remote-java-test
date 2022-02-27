package com.jesse.dockercomposecruddemo.controller;

import com.jesse.dockercomposecruddemo.mapper.PropertyResult;
import com.jesse.dockercomposecruddemo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {

	private final PropertyService propertyService;

	@Autowired
	public PropertyController(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	@GetMapping("/property")
	public PropertyResult getProperties(@RequestParam String postcode) {
		return propertyService.getPropertiesInPostCode(postcode);
	}

	@PostMapping("/property/add")
	public PropertyResult addProperty(@RequestParam long price, @RequestParam int bedrooms,
									  @RequestParam int bathrooms, @RequestParam String number,
									  @RequestParam String address, @RequestParam String region,
									  @RequestParam String postcode, @RequestParam String propertytype) {
		return propertyService.addProperty(price, bedrooms, bathrooms, number, address, region, postcode, propertytype);
	}
}
