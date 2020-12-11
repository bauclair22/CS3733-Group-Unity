package com.amazonaws.lambda.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import httpRequestsAndResponses.CompleteChoiceRequest;
import httpRequestsAndResponses.CompleteChoiceResponse;
import httpRequestsAndResponses.ProduceReportResponse;
import model.Choice;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CompleteChoiceHandlerTest {
    
    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("CompleteChoiceHandler");

        return ctx;
    }

 
    @Test
    public void testSuccessInput() throws IOException {
    	CompleteChoiceHandler handler = new CompleteChoiceHandler();
    	CompleteChoiceRequest req = new Gson().fromJson("0e6d5a23-95cf-4f68-a1f3-a0299141591e",CompleteChoiceRequest.class);
    	CompleteChoiceResponse resp = handler.handleRequest(req, createContext());
        Assert.assertEquals(200, resp.httpCode);
    }
}
