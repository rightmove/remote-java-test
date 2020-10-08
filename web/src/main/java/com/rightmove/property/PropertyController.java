package com.rightmove.property;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class PropertyController {

	@GetMapping("/property")
	public PropertyResult getProperties(@RequestParam String postcode) {
		return new PropertyResult(Collections.emptyList());
	}
}
