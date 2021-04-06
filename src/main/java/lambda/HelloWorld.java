package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class HelloWorld implements RequestHandler<Map<String, String>, String> {
    final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String handleRequest(final Map<String, String> event, final Context context) {
        final LambdaLogger logger = context.getLogger();
        final String response = "Hello, Java world!";
        logger.log("\nENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("\nCONTEXT: " + gson.toJson(context));
        logger.log("\nEVENT: " + gson.toJson(event));
        logger.log("\nEVENT TYPE: " + event.getClass().toString());
        return response;
    }
}