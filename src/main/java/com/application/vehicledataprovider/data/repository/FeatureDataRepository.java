package com.application.vehicledataprovider.data.repository;

import com.application.vehicledataprovider.data.entity.FeatureData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeatureDataRepository extends CrudRepository<FeatureData, String> {



}
