package com.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offer {
	private String loanAmount;
	private String  monthlyPayment;
	private String  term;
	private String  interestRate;
	private String  apr;
	private String  paidToDebt;

}
