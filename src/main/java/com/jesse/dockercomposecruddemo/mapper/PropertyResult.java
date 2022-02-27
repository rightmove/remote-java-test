package com.jesse.dockercomposecruddemo.mapper;

import com.jesse.dockercomposecruddemo.entity.PropertyEntity;

import java.util.List;

public class PropertyResult {

	private final List<PropertyEntity> properties;

	public PropertyResult(List<PropertyEntity> properties) {
		this.properties = properties;
	}

	public List<PropertyEntity> getProperties() {
		return properties;
	}
}
