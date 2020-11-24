package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import database.DAO;
import httpRequestsAndResponses.CreateChoiceRequest;
import httpRequestsAndResponses.CreateChoiceResponse;
import httpRequestsAndResponses.ParticipateChoiceRequest;
import httpRequestsAndResponses.ParticipateChoiceResponse;

public class ParticipateChoiceHandler implements RequestHandler<ParticipateChoiceRequest, ParticipateChoiceResponse>{
	
	LambdaLogger logger;
	private AmazonS3 s3 = null;
	@Override
	public ParticipateChoiceResponse handleRequest(ParticipateChoiceRequest input, Context context) {
		logger = context.getLogger();
        logger.log(input.toString());
		DAO dao = new DAO();
		ParticipateChoiceResponse response;
		try {
			if (dao.addUser(input.getUsername(),input.getPassword(), input.getId())) {//, req.alternatives,req.alternativeTitles
				response = new ParticipateChoiceResponse("Added user to database");
			} else {
				response = new ParticipateChoiceResponse("FAILED TO ADD USER TO SYSTEM", 422);
			}
		}
		catch (Exception e) {
			response = new ParticipateChoiceResponse("Unable to create participant: User: " + input.getUsername() + " (Error Message: "+ e.getMessage() + ")", 400);
		}
		return response;
	}

}
