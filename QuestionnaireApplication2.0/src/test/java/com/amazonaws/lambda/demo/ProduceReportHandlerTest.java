package com.amazonaws.lambda.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import httpRequestsAndResponses.ProduceReportResponse;

public class ProduceReportHandlerTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }
    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("ProduceReportHandler");

        return ctx;
    }

 
    @Test
    public void testSuccessInput() throws IOException {
    	ProduceReportHandler handler = new ProduceReportHandler();
    	ProduceReportResponse resp = handler.handleRequest(input, createContext());
        Assert.assertEquals(200, resp.httpCode);
    }
    
}
