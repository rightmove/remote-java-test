package com.jesse.dockercomposecruddemo.service;

import com.jesse.dockercomposecruddemo.datasetup.PropertyEntityBuilder;
import com.jesse.dockercomposecruddemo.entity.PropertyEntity;
import com.jesse.dockercomposecruddemo.entity.PropertyType;
import com.jesse.dockercomposecruddemo.mapper.PropertyResult;
import com.jesse.dockercomposecruddemo.repository.PropertyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PropertyServiceImplTest {

    @Mock
    PropertyRepository repository;

    @InjectMocks
    PropertyServiceImpl propertyService;

    List<PropertyEntity> propertySet;

    @BeforeEach
    void setUp() {
        propertySet = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void returnsSinglePropertyResult() {
        PropertyEntity property = PropertyEntityBuilder.aDefaultPropertyEntity(1).postcode("W1D 3QU").build();
        propertySet.add(property);
        Mockito.when(repository.findAll()).thenReturn(propertySet);

        assertEquals(1, propertyService.getPropertiesInPostCode("W1D 3QU").getProperties().size());
        assertEquals("W1D 3QU", propertyService.getPropertiesInPostCode("W1D 3QU").getProperties().get(0).getPostcode());
    }

    @Test
    void noPostCodeMatchPropertyResult() {
        PropertyEntity property = PropertyEntityBuilder.aDefaultPropertyEntity(1).postcode("W1F 3QU").build();
        propertySet.add(property);
        Mockito.when(repository.findAll()).thenReturn(propertySet);

        assertEquals(0, propertyService.getPropertiesInPostCode("W1D 3QU").getProperties().size());
    }

    @Test
    void addSinglePropertyResult() {
        PropertyEntity property = new PropertyEntity(0L, 500000L, 3,
                5, "22", "Smith Road",
                "Lancashire", "IG4 RE3", PropertyType.DETACHED);

        Mockito.when(repository.save(property)).thenReturn(null);

        PropertyResult propertyResult = propertyService.addProperty(
                property.getPrice(), property.getBedrooms(), property.getBathrooms(),
                property.getNumber(), property.getAddress(), property.getRegion(),
                property.getPostcode(), property.getType().toString());

        PropertyEntity responseProperty = propertyResult.getProperties().get(0);

        assertEquals(1, propertyResult.getProperties().size());
        assertEquals(property.getId(), responseProperty.getId());
        assertEquals(property.getBedrooms(), responseProperty.getBedrooms());
        assertEquals(property.getBathrooms(), responseProperty.getBathrooms());
        assertEquals(property.getNumber(), responseProperty.getNumber());
        assertEquals(property.getRegion(), responseProperty.getRegion());
        assertEquals(property.getPostcode(), responseProperty.getPostcode());
        assertEquals(property.getType(), responseProperty.getType());
    }
}