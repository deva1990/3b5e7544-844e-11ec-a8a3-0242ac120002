package com.application.vehicledataprovider;

import com.application.vehicledataprovider.controller.GetVehicleDataController;

import com.application.vehicledataprovider.data.repository.VehicleDataRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GetVehicleDataController.class)
@ContextConfiguration(classes = {VehicleDataRepository.class,GetVehicleDataController.class})
class VehicleDataProviderApplicationTests {

    @Autowired
    GetVehicleDataController getVehicleDataController;

    @Test
    public void contextLoads() {
        Assertions.assertThat(getVehicleDataController).isNot(null);
    }

}
