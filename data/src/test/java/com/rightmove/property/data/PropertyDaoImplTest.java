package com.rightmove.property.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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

		assertThat(one).isPresent();
		assertThat(one.get().getId()).isEqualTo(1L);
	}

	@Test
	void shouldReturnOptionalEmptyWhenPropertyNotFound() {
		Optional<PropertyEntity> one = underTest.getOne(1L);

		assertThat(one).isNotPresent();
	}

	@Test
	void shouldReturnDifferentList() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		underTest.save(property);

		Set<PropertyEntity> propertyEntityList = underTest.getAll();

		propertyEntityList.add(new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT));

		Set<PropertyEntity> result = underTest.getAll();

		assertThat(result).hasSize(1);
	}

	@Test
	void shouldUpdateProperty() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);

		PropertyEntity propertyUpdate = new PropertyEntity(1L, 200_000L, 3, 1, "34", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(propertyUpdate);

		Optional<PropertyEntity> result = underTest.getOne(1L);

		assertThat(underTest.getAll()).hasSize(1);
		assertThat(result.get().getPrice()).isEqualTo(200_000L);
		assertThat(result.get().getId()).isEqualTo(1L);
	}

	@Test
	void shouldDeleteAllProperties() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);

		underTest.clear();

		Set<PropertyEntity> propertyEntities = underTest.getAll();

		assertThat(propertyEntities).isEmpty();
	}

	@Test
	void shouldGetAll() {
		PropertyEntity property = new PropertyEntity(1L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);
		PropertyEntity property2 = new PropertyEntity(2L, 100_000L, 3, 1, "33", "Soho Square", "London", "W1D 3QU", PropertyType.FLAT);

		underTest.save(property);
		underTest.save(property2);

		Set<PropertyEntity> propertyEntities = underTest.getAll();

		assertThat(propertyEntities).hasSize(2);
	}

	@Test
	void shouldReturnEmptyListWhenNoProperties() {
		assertThat(underTest.getAll()).isEmpty();
	}
}
