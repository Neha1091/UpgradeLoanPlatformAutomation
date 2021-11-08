package com.api.testcases;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.actions.ResumeApiActions;
import com.api.model.LeadResumptionRequest;
import com.api.model.LeadResumptionResponse;
import com.util.JacksonMapper;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoanOffersTest {
	ResumeApiActions resumeApiActions;

	@BeforeTest

	public void beforeTest() {
		resumeApiActions = new ResumeApiActions();
	}

	@Test
	public void testLoanOffersByLeadApi200Response() {
		LeadResumptionRequest leadResumptionRequest = new LeadResumptionRequest();
		leadResumptionRequest.setLoanAppUuid("b8096ec7-2150-405f-84f5-ae99864b3e96");
		leadResumptionRequest.setSkipSideEffects(true);
		Response response = resumeApiActions.loanOffersByLead(leadResumptionRequest);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
		Assert.assertTrue(response.getStatusLine().contains("OK"));
		JacksonMapper jacksonMapper = new JacksonMapper();
		LeadResumptionResponse leadResumptionResponse = null;
		try {
			leadResumptionResponse = jacksonMapper.deserializeJsonToClass(response.asString(),
					LeadResumptionResponse.class);
		} catch (IOException e) {
			log.error("Couldn't parse the response "+ e.getMessage());
		}
		Assert.assertEquals(leadResumptionResponse.getLoanAppResumptionInfo().getProductType(), "PERSONAL_LOAN",
				"Product Type is PERSONAL_LOAN");

	}

	@Test
	public void testloanOffersByLeadApi404Response() {
		String randomUuid = UUID.randomUUID().toString();
		LeadResumptionRequest leadResumptionRequest = new LeadResumptionRequest();
		leadResumptionRequest.setLoanAppUuid(randomUuid);
		leadResumptionRequest.setSkipSideEffects(true);
		Response response = resumeApiActions.loanOffersByLead(leadResumptionRequest);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		Assert.assertTrue(response.getStatusLine().contains("Not Found"));

	}

}
