package com.amazonaws.lambda.demo;

import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import database.DAO;
import httpRequestsAndResponses.CreateChoiceRequest;
import httpRequestsAndResponses.CreateChoiceResponse;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {
	
	LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	
	String createChoice(String description, int numMembers, String[] alternativeTitles) throws Exception {
		
		if (logger != null) { logger.log("in createChoice"); }
		//Create new ChoiceDAO
		//Check if correct number of alternatives and alternativeTitles
		
		if( 1 < alternativeTitles.length 
				&& alternativeTitles.length <= 5 
				&& numMembers > 0) 
			{
			
			DAO dao = new DAO();
			return dao.createChoice(numMembers, description, alternativeTitles);
		}
		//If parameters incorrect return false
		return null;
	}
	
    @Override
    public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
        logger = context.getLogger();
        logger.log(req.toString());

        CreateChoiceResponse response;
		try {
			String myID = createChoice( req.description, req.numMembers, req.alternativeTitles);
			if (myID != null) {
				response = new CreateChoiceResponse(myID);
			} else {
				response = new CreateChoiceResponse("Unable to create choice", 422);
			}
		}
		catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create Choice: " +  "(" + e.getMessage() + ")", 400);
		}

		return response;
    }

}
