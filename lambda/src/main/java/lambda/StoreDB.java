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
 * Store the itemsets into DynamoDB. NB, it requires 256MB RAM to instance the client
 */
public class StoreDB implements RequestHandler<Object, String> {
    private static final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String handleRequest(final Object input, final Context context) {
        // initialize the values
        final HashMap<String, AttributeValue> item_values = new HashMap<>();
        // initialize the "dataset" attribute
        item_values.put("dataset", new AttributeValue("sales"));
        // initialize the "timestamp" attribute
        item_values.put("timestamp", new AttributeValue("" + System.currentTimeMillis()));
        // initialize the "itemsets" attribute
        item_values.put("itemsets", new AttributeValue(gson.toJson(input)));
        // put the item
        ddb.putItem("frequent-sales", item_values);
        return STATUS_200;
    }
}