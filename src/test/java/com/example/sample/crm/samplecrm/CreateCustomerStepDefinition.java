
package com.example.sample.crm.samplecrm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = {TestConfig.class})
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CreateCustomerStepDefinition {

    @Autowired
    private CustomerProfileController controller;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerProfileService profileService;

    private String createProfileRequest;
    private static final String HAL_MEDIA_TYPE = "application/hal+json";
    private ResultActions resultActions;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(controller,"profileService",profileService);
    }

    @Given("^a user wants to create customer profile with (.*)$")
    public void aUserWantsToCreateCustomerProfileWithValues(final String jsonRequest) throws Throwable {
        this.createProfileRequest = jsonRequest;
        CustomerProfile customerProfile = new ObjectMapper().readValue(jsonRequest, CustomerProfile.class);
        doNothing().when(profileService).createCustomerProfile(customerProfile);
    }


    @When("^when user post a request for create customer profile$")
    public void whenUserPostARequestForCreateCustomerProfile() throws Throwable {
        resultActions = mockMvc.perform(post("/customerprofiles")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(createProfileRequest)
                .accept(HAL_MEDIA_TYPE));
    }


    @Then("^a http status code (\\d+) should be returned$")
    public void aHttpStatusCodeStatusShouldBeReturned(int statusCode) throws Throwable {
        resultActions.andExpect(status().is(statusCode));
    }

    @Given("^customers (.*) are exists in the system$")
    public void customersExistingCustomerAreExistsInTheSystem(String customersData) throws IOException {
        List<CustomerProfile> profiles = new ObjectMapper().readValue(customersData, new TypeReference<List<CustomerProfile>>(){});
        given(profileService.getAllCustomerProfiles()).willReturn(profiles);
    }

    @When("^when user send a get request to get all customer's profile$")
    public void whenUserSendAGetRequestToGetAllCustomerSProfile() throws Throwable {
        resultActions = mockMvc.perform(get("/customerprofiles")
                .contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Given("^customer (.*) with id (\\d) already exists in the system$")
    public void customerCustomerProfileWithIdIdAlreadyExistsInTheSystem(String jsonCustProfile, long id) throws Throwable {
        CustomerProfile customerProfile = new ObjectMapper().readValue(jsonCustProfile, CustomerProfile.class);
        given(profileService.getCustomerProfileById(id)).willReturn(customerProfile);
    }

    @When("^when user send a get request to get customer's profile with id (\\d)$")
    public void whenUserSendAGetRequestToGetCustomerSProfileWithIdId(long id) throws Throwable {
        resultActions = mockMvc.perform(get("/customerprofiles/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @And("^response should be: (.*) json string$")
    public void responseShouldContainCustomerProfile(String expectedCustProfile) throws Throwable {
        resultActions.andExpect(content().json(expectedCustProfile));
    }

    @And("^response should contain : (\\d) number of customers$")
    public void responseShouldContainTotalCustomers(int expectedNumOfProfiles) throws Throwable {
        //TODO assert the total number of customer profiles is 2
        resultActions.andExpect(jsonPath("$.*", hasSize(expectedNumOfProfiles)));
    }
}
