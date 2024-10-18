package com.vendormanagement.rest_demo.service.impl;

import com.vendormanagement.rest_demo.exception.CloudVendorNotFoundException;
import com.vendormanagement.rest_demo.model.CloudVendor;
import com.vendormanagement.rest_demo.repository.CloudVendorRepository;
import com.vendormanagement.rest_demo.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CloudVendorServiceImplTest {
    @Mock
    CloudVendorRepository cloudVendorRepository;
    CloudVendorService cloudVendorService;
    CloudVendor cloudVendor;
    AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor("V1",
                "VS Communications","Tadepalligudem,534101",
                "9876543xxx");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getCloudVendorTest() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById("V1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorService.getCloudVendor("V1")).isEqualTo(cloudVendor);
        assertThat(cloudVendorService.getCloudVendor("V1").getVendorPhoneNumber()).isEqualTo(cloudVendor.getVendorPhoneNumber());
    }


    @Test
    void getCloudVendorNotFoundTest() {
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById("V2")).thenReturn(Optional.empty());
        assertThrows(CloudVendorNotFoundException.class, () -> {
            cloudVendorService.getCloudVendor("V2");
        });
    }

    @Test
    void getAllCloudVendorsTest() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findAll()).thenReturn(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorService.getAllCloudVendors()).isEqualTo(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorPhoneNumber()).isEqualTo(cloudVendor.getVendorPhoneNumber());

    }

    @Test
    void createCloudVendorTest() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Vendor Creation Successful");
    }

    @Test
    void updateCloudVendorTest() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("Vendor Creation Successful");
    }
    @Test
    void deleteCloudVendorTest() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);
        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());
        assertThat(cloudVendorService.deleteCloudVendor("V1")).isEqualTo("Vendor with id V1 Deletion Successful");
    }

}