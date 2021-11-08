package com.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanAppResumptionInfo { 

   private CoBorrowerResumptionInfo coBorrowerResumptionInfo;
   private String loanAppUuid;
   private String addon;
   private String sourceSystem;
   private Boolean turnDown;
   private Double desiredAmount;
   private String referrer;
   private String cashOutAmount;
   private String rewardProgramCode;
   private Boolean checkingDiscountAvailable;
   private Integer loanAppId;
   private String isMobileDiscountApplied;
   private BorrowerResumptionInfo borrowerResumptionInfo;
   //example to deserialize object "availableAppImprovements": [] if needed
   private List<AvailableAppImprovements> availableAppImprovements;
   private Boolean hasLogin;
   private String rewardProgramId;
   private Boolean canAddCollateral;
   private String productType;
   private String status;

}