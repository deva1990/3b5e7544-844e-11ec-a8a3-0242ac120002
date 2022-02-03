package com.application.vehicledataprovider.controller;

import com.application.vehicledataprovider.data.entity.FeatureData;
import com.application.vehicledataprovider.service.GetFeatureDataService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(GetFeatureDataController.class)
class GetFeatureDataControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  GetFeatureDataService getFeatureDataService;

  @BeforeEach
  void setUp() {
  }


  @Test
  void shouldReturnAllFeatureData() throws Exception {

    List<FeatureData> list = new ArrayList<>();
    FeatureData f1 = new FeatureData("LT", "Live Traffic", "DMURUGE",
        new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));
    FeatureData f2 = new FeatureData("RLUL", "Remote Lock/Unlock", "DMURUGE",
        new Date(System.currentTimeMillis()), "DMURUGE", new Date(System.currentTimeMillis()));

    list.add(f1);
    list.add(f2);

    when(getFeatureDataService.getAllFeatures()).thenReturn(list);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllFeatures"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(2)))
        .andExpect(jsonPath("$[0].featureCode", Matchers.is("LT")))
        .andExpect(jsonPath("$[1].featureDesc", Matchers.is("Remote Lock/Unlock")))
        .andReturn();

    String resultDOW = result.getResponse().getContentAsString();
    assertNotNull(resultDOW);
  }

  @Test
  void shouldThrowFeatureNotFoundWhenNoRecordsExistInDB() throws Exception {
    when(getFeatureDataService.getAllFeatures()).thenReturn(new ArrayList<>());
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllFeatures"))
        .andExpect(status().isNotFound())
        .andReturn();
    Assertions.assertEquals(result.getResponse().getContentAsString(), "No Features Found in DB");

  }


  @Test
  void shouldReturnTrueForValidFeature() throws Exception {
    when(getFeatureDataService.isValidFeature("WDD2906611A004819", "RLUL")).thenReturn(true);
    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/isValidFeature/WDD2906611A004819/RLUL"))
        .andExpect(status().isOk())
        .andReturn();
    Assertions.assertEquals(result.getResponse().getContentAsString(), "true");

  }

  @Test
  void shouldReturnFalseForValidFeature() throws Exception {
    when(getFeatureDataService.isValidFeature("WDD2906611A004819", "RLUL")).thenReturn(false);
    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/isValidFeature/WDD2906611A004819/RLUL"))
        .andExpect(status().isOk())
        .andReturn();
    Assertions.assertEquals(result.getResponse().getContentAsString(), "false");

  }
}
