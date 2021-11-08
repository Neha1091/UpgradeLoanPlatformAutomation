package com.api.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.api.model.LeadResumptionRequest;
import com.util.Constants;
import com.util.JacksonMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author ngupta This class is designed to perform Create, Read, Update, Delete
 *         requests 
 */

@Log4j2
public class ResumeApiActions {

	public Map<String, String> setUpHeader() {
		Map<String, String> headers = new HashMap<>();
		headers.put("x-cf-source-id", Constants.X_CF_SOURCE_ID);
		headers.put("x-cf-corr-id", Constants.X_CF_CORR_ID);
		return headers;
	}

	public Response loanOffersByLead(LeadResumptionRequest leadResumptionRequest ) {
		Response response = null;
		try {
		JacksonMapper jacksonMapper = new JacksonMapper();
		String requestBodyJsonObjectAsString=jacksonMapper.serializeClassToJsonString(leadResumptionRequest);
		RestAssured.baseURI = Constants.BASE_RESUME_URI;
		response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).headers(setUpHeader())
				.body(requestBodyJsonObjectAsString).when().post(Constants.POST_LEAD_URI);
		response.then().log().all();
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (Exception e) {			
			log.error(e.getMessage());
		}
		return response;
	}
}