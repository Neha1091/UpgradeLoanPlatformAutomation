package com.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeadResumptionResponse { 

   private LoanAppResumptionInfo loanAppResumptionInfo;
   private List<String> resetOptions;
   //example to deserialize object  "offers": [] if needed
   private List<Offers> offers;
   private String originalLoanApp;
   private String selectedOffer;

}