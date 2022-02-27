package com.jesse.dockercomposecruddemo.mapper;

import com.jesse.dockercomposecruddemo.entity.PropertyType;

public class RequestMapper {

    public PropertyType getPropertyType (String propertyType) {
        switch (propertyType.toLowerCase()) {
            case "detached":
                return PropertyType.DETACHED;
            case "terraced":
                return PropertyType.TERRACED;
            case "semi detached":
                return PropertyType.SEMI_DETACHED;
            default:
                return PropertyType.FLAT;
        }
    }
}
