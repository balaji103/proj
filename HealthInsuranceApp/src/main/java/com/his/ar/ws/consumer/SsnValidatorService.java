package com.his.ar.ws.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.his.ar.bindings.IndvDetailRequest;
import com.his.ar.bindings.IndvDetailResponse;

public class SsnValidatorService extends WebServiceGatewaySupport{

		private static final Logger log = LoggerFactory.getLogger(SsnValidatorService.class);

		public IndvDetailResponse validateUserBySsnAndDob(String dob,Long ssn) {
			IndvDetailRequest request = new IndvDetailRequest();
			request.setSsn(ssn);
			request.setDob(dob);

			log.info("Requesting user for validate By ssn num : " + ssn +"and DOB : "+dob);
			return (IndvDetailResponse) getWebServiceTemplate().marshalSendAndReceive(request);
		}

	}
