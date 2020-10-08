package com.rightmove.property.data;

import java.util.Objects;

public class PropertyEntity {
	private final long id;
	private final long price;
	private final int bedrooms;
	private final Integer bathrooms;
	private final String number;
	private final String address;
	private final String region;
	private final String postcode;
	private final PropertyType type;

	public PropertyEntity(long id, long price, int bedrooms, Integer bathrooms, String number, String address, String region, String postcode, PropertyType type) {
		this.id = id;
		this.price = price;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.number = number;
		this.address = address;
		this.region = region;
		this.postcode = postcode;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public long getPrice() {
		return price;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public Integer getBathrooms() {
		return bathrooms;
	}

	public String getNumber() {
		return number;
	}

	public String getAddress() {
		return address;
	}

	public String getRegion() {
		return region;
	}

	public String getPostcode() {
		return postcode;
	}

	public PropertyType getType() {
		return type;
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
