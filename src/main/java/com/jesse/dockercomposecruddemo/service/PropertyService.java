package com.jesse.dockercomposecruddemo.service;

import com.jesse.dockercomposecruddemo.mapper.PropertyResult;

public interface PropertyService {

    PropertyResult getPropertiesInPostCode (String postCode);

    // I know, I know too many parameters. The objective of this excersise is to get a DB working using docker compose
    PropertyResult addProperty(long price, int bedrooms,
                                      int bathrooms, String number,
                                      String address, String region,
                                      String postCode, String propertyType);
}
