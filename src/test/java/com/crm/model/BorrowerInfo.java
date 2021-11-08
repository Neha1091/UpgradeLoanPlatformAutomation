package com.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowerInfo { 

   private String firstName;
   private String lastName;
   private String street;
   private String city;
   private String state;
   private String zipCode;
   private String dateOfBirth;

}