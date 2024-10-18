package com.vendormanagement.rest_demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vendormanagement.rest_demo.model.CloudVendor;
import com.vendormanagement.rest_demo.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CloudVendorService cloudVendorService;
    CloudVendor cloudVendor1;
    CloudVendor cloudVendor2;
    List<CloudVendor> cloudVendorList =new ArrayList<>();
    @BeforeEach
    void setUp() {
        cloudVendor1 = new CloudVendor("V1", "AHA", "HYDERABAD,INDIA", "89076543XX");
        cloudVendor2 = new CloudVendor("V2", "PRIME", "CALIFORNIA,USA", "+123 987655XX");
        cloudVendorList.add(cloudVendor1);
        cloudVendorList.add(cloudVendor2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCloudVendorDetailsTest() throws Exception {
        when(cloudVendorService.getCloudVendor("V1"))
                .thenReturn(cloudVendor1);
        this.mockMvc.perform(get("/cloudvendor/V1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllCloudVendorDetailsTest() throws Exception{
        when(cloudVendorService.getAllCloudVendors())
                .thenReturn(cloudVendorList);
        this.mockMvc.perform(get("/cloudvendor"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createCloudVendorDetailsTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJSON = ow.writeValueAsString(cloudVendor1);
        when(cloudVendorService.createCloudVendor(cloudVendor1))
                .thenReturn("Vendor Creation Successful");
        this.mockMvc.perform(post("/cloudvendor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateCloudVendorDetails() throws Exception{
        CloudVendor cloudVendor3 = new CloudVendor("V1","AHA PVT LTD","HYDERABAD", "9876543120");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJSON = ow.writeValueAsString(cloudVendor3);
        when(cloudVendorService.updateCloudVendor(cloudVendor3))
                .thenReturn("Vendor Creation Successful");
        this.mockMvc.perform(put("/cloudvendor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteCloudVendorDetailsTest() throws Exception{
        when(cloudVendorService.deleteCloudVendor("V1"))
                .thenReturn("Vendor with id V1 Deletion Successful");
        this.mockMvc.perform(delete("/cloudvendor/V1"))
                .andDo(print()).andExpect(status().isOk());
    }
}