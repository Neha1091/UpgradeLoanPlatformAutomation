package com.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowerResumptionInfo { 

   private Boolean ssnRequired;
   private String firstName;
   private String lastName;
   private String maskedEmail;
   private String street;
   private String city;
   private String state;
   private String zipCode;
   private String email;
   private String dateOfBirth;

}