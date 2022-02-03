package com.application.vehicledataprovider.service;

import com.application.vehicledataprovider.data.entity.VehicleData;
import com.application.vehicledataprovider.data.repository.VehicleDataRepository;
import com.application.vehicledataprovider.exception.VehicleServiceException;
import com.application.vehicledataprovider.service.impl.GetVehicleDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetVehicleDataServiceImplTest {

    @Mock
    private VehicleDataRepository vehicleDataRepository;

    @InjectMocks
    private GetVehicleDataServiceImpl getVehicleDataServiceImpl;

    @Test
    void getAllVehicleData() throws VehicleServiceException {
        List<VehicleData> list = new ArrayList<>();

        VehicleData v1 = new VehicleData("WDD2906611A004819", "Bob", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),"GLA", "White", "Y", "DMURUGE",
                new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));
        VehicleData v2 = new VehicleData("WDD2906611A004820", "Bob", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),"GLA", "White", "Y", "DMURUGE",
                new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));

        list.add(v1);
        list.add(v2);

        when(vehicleDataRepository.findAll()).thenReturn(list);

        //test
        List<VehicleData> vehicleDataList = getVehicleDataServiceImpl.getAllVehicleData();

        assertEquals(2, vehicleDataList.size());
        verify(vehicleDataRepository, times(1)).findAll();
    }

    @Test
    void getVehicleData() throws VehicleServiceException {

        VehicleData v1 = new VehicleData("WDD2906611A004819", "Bob", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),"GLA", "White", "Y", "DMURUGE",
                new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));


        when(vehicleDataRepository.findById("WDD2906611A004819")).thenReturn(Optional.of(v1));

        //test
        VehicleData vehicleData= getVehicleDataServiceImpl.getVehicleData("WDD2906611A004819");

        assertEquals("White", vehicleData.getColor());
        verify(vehicleDataRepository, times(1)).findById("WDD2906611A004819");
    }
}
