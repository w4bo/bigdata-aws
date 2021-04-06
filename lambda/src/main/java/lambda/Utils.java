package lambda;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static final String STATUS_200 = "{\"statusCode\": \"200\"}";

    public static void invokeLambda(final String functionName, final Object payload) {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final InvokeRequest invokeRequest = new InvokeRequest().withFunctionName(functionName).withPayload(gson.toJson(payload));

        // Sync call
        final AWSLambda awsLambda = AWSLambdaClientBuilder.standard().build();
        final InvokeResult res = awsLambda.invoke(invokeRequest);
        awsLambda.shutdown();

        // Async call
        // final AWSLambdaAsync awsAsyncLambda = AWSLambdaAsyncClientBuilder.defaultClient();
        // final Future<InvokeResult> future_res = awsAsyncLambda.invokeAsync(invokeRequest);
        // try {
        //     final InvokeResult res = future_res.get(10, TimeUnit.SECONDS);
        //     System.out.println(res.getStatusCode());
        // } catch (final Exception e) {
        //     e.printStackTrace();
        // }
    }
}
