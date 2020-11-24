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
	
	boolean createChoice(String title, String description, int numMembers) throws Exception {
		//, String[] alternatives,String[] alternativeTitles
		if (logger != null) { logger.log("in createChoice"); }
		//Create new ChoiceDAO
		//Check if correct number of alternatives and alternativeTitles
		if(true ) //1 < alternatives.length && alternatives.length <= 5 && 1 < alternativeTitles.length && alternativeTitles.length <= 5 && alternatives.length == alternativeTitles.length&& numMembers > 0
			{
			
			DAO dao = new DAO();
			return (dao.createChoice(numMembers, description) != null);
		}
		//If parameters incorrect return false
		return false;
	}
	
    @Override
    public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
        logger = context.getLogger();
        logger.log(req.toString());

        CreateChoiceResponse response;
		try {
			if (createChoice(req.title, req.description, req.numMembers)) {//, req.alternatives,req.alternativeTitles
				response = new CreateChoiceResponse(req.title);
			} else {
				response = new CreateChoiceResponse(req.title, 422);
			}
		}
		catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create Choice: " + req.title + "(" + e.getMessage() + ")", 400);
		}

		return response;
    }

}
