package com.jesse.dockercomposecruddemo.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class PropertyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private long price;

	@NotNull
	private int bedrooms;

	@NotNull
	private Integer bathrooms;

	@NotNull
	private String number;

	@NotNull
	private String address;

	@NotNull
	private String region;

	@NotNull
	private String postcode;

	@NotNull
	private PropertyType type;

	public PropertyEntity() {

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PropertyEntity that = (PropertyEntity) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
