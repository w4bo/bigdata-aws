package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static lambda.Utils.STATUS_200;
import static lambda.Utils.invokeLambda;

/**
 * Preprocess the Sales dataset
 */
public class Preprocess implements RequestHandler<Object, String> {

    /**
     * Unnest the product sales
     * @param jsonObject dataset to modify as a JSON object
     * @return modified dataset
     */
    public List<List<String>> unnest(final String jsonObject) {
        try {
            final JSONArray sales = new JSONArray(jsonObject);
            final List<List<String>> acc = new ArrayList<>();
            sales.forEach(i -> {
                final List<String> cur = new ArrayList<>();
                final JSONObject curSales = (JSONObject) i;
                final JSONArray products = curSales.getJSONArray("products");
                products.forEach(c -> cur.add(c.toString()));
                acc.add(cur);
            });
            return acc;
        } catch (final Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public String handleRequest(final Object input, final Context context) {
        invokeLambda("FIM", unnest(input.toString()));
        return STATUS_200;
    }
}
