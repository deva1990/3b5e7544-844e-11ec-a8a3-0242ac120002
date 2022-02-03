package com.application.vehicledataprovider.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.application.vehicledataprovider.data.entity.FeatureData;
import com.application.vehicledataprovider.data.entity.VehicleData;
import com.application.vehicledataprovider.data.entity.VehicleFeatureData;
import com.application.vehicledataprovider.service.impl.GetFeatureDataServiceImpl;
import com.application.vehicledataprovider.data.repository.FeatureDataRepository;
import com.application.vehicledataprovider.data.repository.VehicleFeatureDataRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetFeatureDataServiceImplTest {

    @Mock
    private FeatureDataRepository featureDataRepository;

    @Mock
    private VehicleFeatureDataRepository vehicleFeatureDataRepository;

    @InjectMocks
    private GetFeatureDataServiceImpl getFeatureDataService;

    @Test
    void getAllVehicleData() {
        List<FeatureData> list = new ArrayList<>();
        FeatureData f1 = new FeatureData("LT", "Live Traffic", "DMURUGE",
            new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));
        FeatureData f2 = new FeatureData("RLUL", "Remote Lock/Unlock", "DMURUGE",
            new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));

        list.add(f1);
        list.add(f1);

        when(featureDataRepository.findAll()).thenReturn(list);

        //test
        List<FeatureData> featureDataList = getFeatureDataService.getAllFeatures();

        assertEquals(2, featureDataList.size());
        verify(featureDataRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnTrueForValidFeature() {

        VehicleFeatureData vehicleFeatureData = new VehicleFeatureData(100L,
            new VehicleData("WDD2906611A004819", "Bob", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),"GLA", "White", "Y", "DMURUGE",new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis())),
            new FeatureData("LT", "Live Traffic", "DMURUGE",new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis())),
            "DMURUGE",new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis())
            );

        when(vehicleFeatureDataRepository.findById("WDD2906611A004819")).thenReturn(Optional.of(vehicleFeatureData));

        boolean isValidFeature =  getFeatureDataService.isValidFeature("WDD2906611A004819","LT");

        assertTrue(isValidFeature);
    }
}
