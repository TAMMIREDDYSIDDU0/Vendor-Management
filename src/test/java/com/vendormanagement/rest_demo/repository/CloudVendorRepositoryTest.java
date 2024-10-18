package com.vendormanagement.rest_demo.repository;


import com.vendormanagement.rest_demo.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CloudVendorRepositoryTest{
    /*
    GIVEN
    WHEN
    THEN
     */
    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;
    @BeforeEach
    void setUp() {
        cloudVendor = new CloudVendor("V1",
                "Amazon","USA",
                "9876543210");
        cloudVendorRepository.save(cloudVendor);

    }
    @AfterEach
    void tearDown() {
        cloudVendor =null;
        cloudVendorRepository.deleteAll();
    }

    @Test
    void getVendorDetailsByName_Success_Test(){
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Amazon");
        assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
        assertThat(cloudVendorList.size()).isEqualTo(1);
        assertThat(cloudVendorList.get(0).getVenderAddress()).isEqualTo(cloudVendor.getVenderAddress());
    }

    @Test
    void getVendorDetailsByName_Failure_Test(){
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("flipkart");
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }
}
