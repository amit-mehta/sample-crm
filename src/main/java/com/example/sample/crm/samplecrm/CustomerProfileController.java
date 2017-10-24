package com.example.sample.crm.samplecrm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin
public class CustomerProfileController {

    private CustomerProfileService profileService;

    @Autowired
    public CustomerProfileController(CustomerProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method = GET, value = "/customerprofiles")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerProfile> getCustomerProfiles() {
        return profileService.getAllCustomerProfiles();
    }

    @RequestMapping(method = GET, value = "customerprofiles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerProfile getCustomerProfile(final @PathVariable Long id) {
        return profileService.getCustomerProfileById(id);
    }

    @RequestMapping(method = POST, value = "/customerprofiles")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomerProfile(final @RequestBody CustomerProfile customerProfile) {
        profileService.createCustomerProfile(customerProfile);
    }

    @RequestMapping(method = PUT, value = "customerprofiles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomerProfile(@PathVariable final Long id, final @RequestBody CustomerProfile customerProfile) {
        profileService.updateCustomerProfile(id, customerProfile);
    }

}
