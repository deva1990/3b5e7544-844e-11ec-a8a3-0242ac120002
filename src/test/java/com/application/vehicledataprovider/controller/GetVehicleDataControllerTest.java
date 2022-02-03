package com.application.vehicledataprovider.controller;

import com.application.vehicledataprovider.data.entity.VehicleData;
import com.application.vehicledataprovider.service.GetVehicleDataService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(GetVehicleDataController.class)
class GetVehicleDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GetVehicleDataService getVehicleDataService;

    @BeforeEach
    void setUp() {
    }



    @Test
    void shouldReturnAllAvailableVehicleData() throws Exception {

        List<VehicleData> list = new ArrayList<>();
        VehicleData v1 = new VehicleData("WDD2906611A004819", "Bob", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),"GLA", "White", "Y", "DMURUGE",
                new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));
        VehicleData v2 = new VehicleData("WDD2906611A004820", "Bob", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),"GLA", "White", "Y", "DMURUGE",
                new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));

        list.add(v1);
        list.add(v2);

        when(getVehicleDataService.getAllVehicleData()).thenReturn(list);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllVehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].vin", Matchers.is("WDD2906611A004819")))
                .andExpect(jsonPath("$[1].vin", Matchers.is("WDD2906611A004820")))
                .andReturn();

        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
    }

    @Test
    void shouldThrowVehicleNotFoundWhenNoRecordsExistInDB() throws Exception {
        when(getVehicleDataService.getAllVehicleData()).thenReturn(new ArrayList<>());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllVehicles"))
                .andExpect(status().isNotFound())
                .andReturn();
        Assertions.assertEquals(result.getResponse().getContentAsString(),"No Vehicle Found in DB");
    }

    @Test
    void shouldReturnVehicleDataForValidVehicle() throws Exception {
        VehicleData v1 = new VehicleData("WDD2906611A004819", "Bob", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),"GLA", "White", "Y", "DMURUGE",
                new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));

        when(getVehicleDataService.getVehicleData("WDD2906611A004819")).thenReturn(v1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getVehicle/WDD2906611A004819"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("ownerName", Matchers.is("Bob")));
    }

    @Test
    void shouldReturnTCUEnabledYes() throws Exception {
        VehicleData v1 = new VehicleData("WDD2906611A004819", "Bob", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),"GLA", "White", "Y", "DMURUGE",
                new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));

        when(getVehicleDataService.getVehicleData("WDD2906611A004819")).thenReturn(v1);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/isTCUEnabled/WDD2906611A004819"))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals("Y",response,"TCU Enabled Status should be Y");
    }
}
