package com.jesse.dockercomposecruddemo.service;

import com.jesse.dockercomposecruddemo.entity.PropertyEntity;
import com.jesse.dockercomposecruddemo.mapper.PropertyResult;
import com.jesse.dockercomposecruddemo.mapper.RequestMapper;
import com.jesse.dockercomposecruddemo.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository repository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository repository) {
        this.repository = repository;
    }

    public PropertyResult getPropertiesInPostCode(String postCode) {
        List<PropertyEntity> propertyEntityList = new ArrayList<>();

        repository.findAll().forEach(propertyEntity -> {
            if (propertyEntity.getPostcode().equals(postCode))
                propertyEntityList.add(propertyEntity);
        });

        return new PropertyResult(propertyEntityList);
    }

    public PropertyResult addProperty(long price, int bedrooms,
                                      int bathrooms, String number,
                                      String address, String region,
                                      String postCode, String propertyType) {

        PropertyEntity property = new PropertyEntity(0L,
                price, bedrooms, bathrooms,
                number, address, region,
                postCode, new RequestMapper().getPropertyType(propertyType));

        repository.save(property);

        List<PropertyEntity> propertyEntityList = new ArrayList<>();
        propertyEntityList.add(property);

        return new PropertyResult(propertyEntityList);
    }
}
