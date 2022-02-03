package com.application.vehicledataprovider.data.repository;

import com.application.vehicledataprovider.data.entity.VehicleData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VehicleDataRepositoryTest {

    @Autowired
    VehicleDataRepository vehicleDataRepository;

    @Test
    public void testCreateReadDelete() {
        VehicleData v1 = new VehicleData("WDD2906611A004819", "Bob", new Date(System.currentTimeMillis()),  new Date(System.currentTimeMillis()), "GLA", "White", "Y", "DMURUGE",
                new Date(System.currentTimeMillis()), "DMURUGE",  new Date(System.currentTimeMillis()));

        vehicleDataRepository.save(v1);

        Iterable<VehicleData> vehicleDataList = vehicleDataRepository.findAll();
        Assertions.assertThat(vehicleDataList).extracting(VehicleData::getVIN).contains("WDD2906611A004819");

        vehicleDataRepository.deleteAll();
        Assertions.assertThat(vehicleDataRepository.findAll()).isEmpty();
    }
}
