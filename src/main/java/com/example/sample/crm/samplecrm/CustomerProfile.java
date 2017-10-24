package com.example.sample.crm.samplecrm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@ApiModel
@Data
public class CustomerProfile {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date dateOfBirth;

    private String homeAddress;

    private String officeAddress;

    private String emailAddress;
}
