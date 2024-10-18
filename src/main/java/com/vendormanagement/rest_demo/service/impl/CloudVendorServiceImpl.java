package com.vendormanagement.rest_demo.service.impl;

import com.vendormanagement.rest_demo.exception.CloudVendorNotFoundException;
import com.vendormanagement.rest_demo.model.CloudVendor;
import com.vendormanagement.rest_demo.repository.CloudVendorRepository;
import com.vendormanagement.rest_demo.service.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

    CloudVendorRepository cloudVendorRepository;
    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }
    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Vendor Creation Successful";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Vendor Creation Successful";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        cloudVendorRepository.deleteById(cloudVendorId);
        return "Vendor with id " + cloudVendorId+  " Deletion Successful";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
       if(cloudVendorRepository.findById(cloudVendorId).isEmpty())
           throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
        return cloudVendorRepository.findById(cloudVendorId).get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();
    }
}
