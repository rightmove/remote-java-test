package com.rightmove.property.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PropertyDaoImplTest {

	private PropertyDaoImpl underTest;

	@BeforeEach
	void setup() {
		underTest = new PropertyDaoImpl();
	}

	@AfterEach
	void tearDown() {
		underTest.clear();
	}

	@Test
	void shouldSaveProperty() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		underTest.save(property);

		Optional<PropertyEntity> one = underTest.getOne(1L);

		assertTrue(one.isPresent());
		assertEquals(1L, one.get().getId());
	}

	@Test
	void shouldReturnOptionalEmptyWhenPropertyNotFound() {
		Optional<PropertyEntity> one = underTest.getOne(1L);

		assertTrue(one.isEmpty());
	}

	@Test
	void shouldReturnDifferentList() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		underTest.save(property);

		Set<PropertyEntity> propertyEntityList = underTest.getAll();

		propertyEntityList.add(new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT));

		Set<PropertyEntity> result = underTest.getAll();

		assertEquals(1, result.size());
	}

	@Test
	void shouldUpdateProperty() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);

		PropertyEntity propertyUpdate = new PropertyEntity(1L, 200_000L, 3, 1, "34", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(propertyUpdate);

		Optional<PropertyEntity> result = underTest.getOne(1L);

		assertEquals(1, underTest.getAll().size());
		assertEquals(200_000L, result.get().getPrice());
		assertEquals(1L, result.get().getId());
	}

	@Test
	void shouldDeleteAllProperties() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);

		underTest.clear();

		Set<PropertyEntity> propertyEntities = underTest.getAll();

		assertTrue(propertyEntities.isEmpty());
	}

	@Test
	void shouldGetAll() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		PropertyEntity property2 = new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);
		underTest.save(property2);

		Set<PropertyEntity> propertyEntities = underTest.getAll();

		assertEquals(2, propertyEntities.size());
	}

	@Test
	void shouldReturnEmptyListWhenNoProperties() {
		assertTrue(underTest.getAll().isEmpty());
	}
}
