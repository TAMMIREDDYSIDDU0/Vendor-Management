package com.vendormanagement.rest_demo.controller;

import com.vendormanagement.rest_demo.model.CloudVendor;
import com.vendormanagement.rest_demo.response.ResponseHandler;
import com.vendormanagement.rest_demo.service.CloudVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {
    CloudVendorService cloudVendorService;
    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }
    @GetMapping
    public List<CloudVendor> getCloudVendorDetails(){
        return cloudVendorService.getAllCloudVendors();
    }
    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
       return ResponseHandler.responseBuilder(
                "Fetch Successful", HttpStatus.OK,
                cloudVendorService.getCloudVendor(vendorId));
    }

    @PostMapping
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        return cloudVendorService.createCloudVendor(cloudVendor);
    }
    @PutMapping
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        return cloudVendorService.updateCloudVendor(cloudVendor);
    }
    @DeleteMapping("{vendorId}")
    public String createCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        return cloudVendorService.deleteCloudVendor(vendorId);
    }
}