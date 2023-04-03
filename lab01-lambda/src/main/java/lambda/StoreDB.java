package lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import static lambda.Utils.STATUS_200;

/**
 * Store the itemsets into DynamoDB. NB, it requires 256MB RAM to instance the client.
 */
public final class StoreDB implements RequestHandler<Object, String> {
    private static final AmazonDynamoDB DDBS = AmazonDynamoDBClientBuilder.defaultClient();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String handleRequest(final Object input, final Context context) {
        // initialize the values
        final HashMap<String, AttributeValue> itemValues = new HashMap<>();
        // initialize the "dataset" attribute
        itemValues.put("dataset", new AttributeValue("sales"));
        // initialize the "timestamp" attribute
        itemValues.put("timestamp", new AttributeValue("" + System.currentTimeMillis()));
        // initialize the "itemsets" attribute
        itemValues.put("itemsets", new AttributeValue(gson.toJson(input)));
        // put the item
        DDBS.putItem("frequent-sales", itemValues);
        return STATUS_200;
    }
}
