package com.jesse.dockercomposecruddemo.repository;

import com.jesse.dockercomposecruddemo.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {

    Set<PropertyEntity> findByPostcode(String postCode);

}
