package com.rightmove.property.data;

import java.util.Objects;

public record PropertyEntity(
        long id,
        long price,
        int bedrooms,
        Integer bathrooms,
        String number,
        String address,
        String region,
        String postcode,
        PropertyType type
) {
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
