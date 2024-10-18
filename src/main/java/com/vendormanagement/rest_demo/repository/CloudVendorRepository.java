package com.vendormanagement.rest_demo.repository;

import com.vendormanagement.rest_demo.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;

/*
justifying JpaRepository<CloudVendor, String>
Here CloudVendor(1st Arg) is a Entity where we are willing perform
operations and String as 2nd Arg because the
primary key (vendorId) is of String
 */
public interface CloudVendorRepository extends JpaRepository<CloudVendor, String> {

}
