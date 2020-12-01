package com.amazonaws.lambda.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import httpRequestsAndResponses.CreateChoiceResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateChoiceHandlerTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }
    
    public CreateChoiceResponse testCreateChoice_Alternative() throws Exception {// must not be void, this is temporary i dont know if correct
    	CreateChoiceHandler ch = new CreateChoiceHandler();
    	String[] alts= {"movie1","movie2"};
    	String id = ch.createChoice("movie to watch", 5, alts);
    	//ch.createAlternative("scary movie", 16);
    	return null;
    }
}
