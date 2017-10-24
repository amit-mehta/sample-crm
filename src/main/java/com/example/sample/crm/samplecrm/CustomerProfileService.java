package com.example.sample.crm.samplecrm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerProfileService {

    private CustomerProfileRepository repository;

    @Autowired
    public CustomerProfileService(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    public List<CustomerProfile> getAllCustomerProfiles() {
        return repository.findAll();
    }

    public CustomerProfile getCustomerProfileById(Long profileId) {
        CustomerProfile profile = repository.findOne(profileId);
        if(profile != null){
            return profile;
        }
        throw new ResourceNotFoundException();
    }

    public void createCustomerProfile(CustomerProfile customerProfile) {
        repository.save(customerProfile);
    }


    public void updateCustomerProfile(Long profileId, CustomerProfile newProfile) {
        CustomerProfile profile = repository.findOne(profileId);
        if(profile == null) {
            throw new ResourceNotFoundException();
        }
        profile.setFirstName(newProfile.getFirstName());
        profile.setLastName(newProfile.getLastName());
        profile.setDateOfBirth(newProfile.getDateOfBirth());
        repository.save(profile);
    }

    public void deleteCustomerProfile(Long id) {
        if(repository.exists(id)) {
            repository.delete(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
