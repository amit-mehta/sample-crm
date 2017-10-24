package com.example.sample.crm.samplecrm;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerProfileServiceTest {

    @InjectMocks
    private CustomerProfileService profileService;

    @Mock
    private CustomerProfileRepository repository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetCustomerProfile() {
        List<CustomerProfile> mockList = mock(List.class);
        given(repository.findAll()).willReturn(mockList);

        List<CustomerProfile> customerProfiles = profileService.getAllCustomerProfiles();
        assertThat(customerProfiles).isEqualTo(mockList);
        verify(repository).findAll();
    }

    @Test
    public void testGetCustomerProfileById() {
        Long testId = 1L;
        CustomerProfile mockProfile = mock(CustomerProfile.class);
        given(repository.findOne(testId)).willReturn(mockProfile);

        CustomerProfile profile = profileService.getCustomerProfileById(testId);

        assertThat(profile).isEqualTo(mockProfile);
        verify(repository).findOne(testId);
    }

    @Test
    public void testGetCustomerProfileByIdWhenProfileDoesntExist() {
        Long testId = 1L;
        given(repository.findOne(testId)).willReturn(null);
        exception.expect(ResourceNotFoundException.class);

        profileService.getCustomerProfileById(testId);
    }

    @Test
    public void testCreateCustomerProfile() {
        CustomerProfile mockCustomer = mock(CustomerProfile.class);
        given(repository.save(mockCustomer)).willReturn(mockCustomer);

        profileService.createCustomerProfile(mockCustomer);

        verify(repository).save(mockCustomer);
    }

    @Test
    public void testUpdateCustomerProfile() throws ParseException {
        Long testId = 1L;
        CustomerProfile mockProfile = new CustomerProfile();
        java.util.Date dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2017");
        mockProfile.setDateOfBirth(dateOfBirth);
        mockProfile.setFirstName("TestFirst");
        mockProfile.setLastName("TestLast");
        given(repository.findOne(testId)).willReturn(mockProfile);
        CustomerProfile newProfile = new CustomerProfile();
        newProfile.setFirstName("NewFirst");
        newProfile.setLastName("NewLast");
        Date sampleNewDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2017");
        newProfile.setDateOfBirth(sampleNewDate);

        profileService.updateCustomerProfile(testId, newProfile);

        ArgumentCaptor<CustomerProfile> captor = ArgumentCaptor.forClass(CustomerProfile.class);
        verify(repository).save(captor.capture());
        CustomerProfile savedProfile = captor.getValue();
        assertThat(savedProfile.getFirstName()).isEqualTo("NewFirst");
        assertThat(savedProfile.getLastName()).isEqualTo("NewLast");
        assertThat(savedProfile.getDateOfBirth()).isEqualTo(sampleNewDate);
    }

    @Test
    public void testUpdateCustomerProfileWhenNoProfileFound() {
        Long testId = 1L;
        given(repository.findOne(testId)).willReturn(null);
        exception.expect(ResourceNotFoundException.class);

        profileService.updateCustomerProfile(testId, new CustomerProfile());
    }

}