package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import database.DAO;
import httpRequestsAndResponses.CreateFeedbackRequest;
import httpRequestsAndResponses.CreateFeedbackResponse;
import model.Choice;
import model.Feedback;

public class CreateFeedbackHandler implements RequestHandler<CreateFeedbackRequest, CreateFeedbackResponse> {
	
	LambdaLogger logger;
	
	Feedback createFeedback(String memberID, String altid, String feedback) throws Exception {
		
		if (logger != null) { logger.log("in createFeedback");
			
			DAO dao = new DAO();
			return dao.giveFeedback(memberID, altid, feedback);
		}
		return null;
	}

    @Override
    public CreateFeedbackResponse handleRequest(CreateFeedbackRequest req, Context context) {
    	 logger = context.getLogger();
         logger.log(req.toString());
         DAO dao = new DAO();

         CreateFeedbackResponse response;
 		try {
 			Feedback myFeedback = createFeedback( req.getMemberID(), req.getAltid(), req.getDescription());
 			if (myFeedback != null) {
 				Choice myChoice = dao.getChoiceswithID(req.getChoiceID());
 				response = new CreateFeedbackResponse(myChoice);
 			} else {
 				response = new CreateFeedbackResponse("Unable to create Feedback", 422);
 			}
 		}
 		catch (Exception e) {
 			response = new CreateFeedbackResponse("Unable to create Feedback: " +  "(" + e.getMessage() + ")", 400);
 		}

 		return response;
    }

}
