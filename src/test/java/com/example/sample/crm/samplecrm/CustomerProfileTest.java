package com.example.sample.crm.samplecrm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {
                "html:target/cucumber/customerprofile.html",
                "json:target/cucumber/customerprofile.json",
                "pretty:target/cucumber/customerprofile.txt",
                "junit:target/cucumber/customerprofile.xml"},
        features = {
                "classpath:create-customer.feature",
                "classpath:get-customer.feature",
                "classpath:get-customer-byid.feature"
        }
)
public class CustomerProfileTest {
}
