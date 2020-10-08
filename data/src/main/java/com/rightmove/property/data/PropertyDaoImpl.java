package com.rightmove.property.data;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * A simple in memory implementation of the {@link PropertyDao}.
 */
@Component
public class PropertyDaoImpl implements PropertyDao {

	private static Map<Long, PropertyEntity> properties = new HashMap<>();

	@Override
	public Optional<PropertyEntity> getOne(long id) {
		return Optional.ofNullable(properties.get(id));
	}

	@Override
	public Set<PropertyEntity> getAll() {
		return new HashSet<>(properties.values());
	}

	@Override
	public void clear() {
		properties.clear();
	}

	@Override
	public void save(PropertyEntity propertyEntity) {
		properties.put(propertyEntity.getId(), propertyEntity);
	}
}
