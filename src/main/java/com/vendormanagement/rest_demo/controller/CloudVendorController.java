package com.vendormanagement.rest_demo.controller;

import com.vendormanagement.rest_demo.model.CloudVendor;
import com.vendormanagement.rest_demo.response.ResponseHandler;
import com.vendormanagement.rest_demo.service.CloudVendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {
    private static final Logger logger = LoggerFactory.getLogger(CloudVendorService.class);
    CloudVendorService cloudVendorService;
    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }
    @GetMapping
    public List<CloudVendor> getAllCloudVendorDetails(){
        logger.info("rest-demo.CloudVendorController.getCloudVendorDetails() :: Getting all the Vendor Details...");
        return cloudVendorService.getAllCloudVendors();
    }
    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        logger.info("rest-demo.CloudVendorController.getCloudVendorDetails() :: Getting the Vendor Details...");
       return ResponseHandler.responseBuilder(
                "Fetch Successful", HttpStatus.OK,
                cloudVendorService.getCloudVendor(vendorId));
    }

    @PostMapping
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        logger.info("rest-dem0.CloudVendorController.createCloudVendorDetails() :: Vendor Details Creation Successful...");
        return cloudVendorService.createCloudVendor(cloudVendor);
    }
    @PutMapping
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        logger.info("rest-dem0.CloudVendorController.updateCloudVendorDetails() :: Vendor Details Updation Successful...");
        return cloudVendorService.updateCloudVendor(cloudVendor);
    }
    @DeleteMapping("{vendorId}")
    public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        logger.info("rest-dem0.CloudVendorController.CloudVendorDetails() :: Vendor Details Deletion Successful...");
        logger.debug("test debug logs");
        return cloudVendorService.deleteCloudVendor(vendorId);
    }
}