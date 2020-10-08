package com.rightmove.property;

import com.rightmove.property.data.PropertyEntity;

import java.util.List;

public class PropertyResult {

	private List<PropertyEntity> properties;

	public PropertyResult(List<PropertyEntity> properties) {
		this.properties = properties;
	}

	public List<PropertyEntity> getProperties() {
		return properties;
	}
}
