package lambda;

import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPreprocess {

    @Test
    void test() {
        final JSONObject alice = new JSONObject();
        final JSONObject bob = new JSONObject();
        final JSONObject charlie = new JSONObject();
        alice.put("customerName", "Alice");
        alice.put("products", Lists.newArrayList("Pizza", "Beer", "Diaper"));
        bob.put("customerName", "Bob");
        bob.put("products", Lists.newArrayList("Pizza", "Beer", "Diaper"));
        charlie.put("customerName", "Charlie");
        charlie.put("products", Lists.newArrayList("Pizza", "Cola"));
        final JSONArray sales = new JSONArray(Lists.newArrayList(alice, bob, charlie));
        final List<List<String>> unnest = new Preprocess().unnest(sales.toString());
        assertEquals(unnest.get(0), Lists.newArrayList("Pizza", "Beer", "Diaper"));
        assertEquals(unnest.get(2), Lists.newArrayList("Pizza", "Cola"));
    }

    @Test
    void testDataset() {
        final List<List<String>> unnest = new Preprocess().unnest(ReadDataset.read());
        assertEquals(unnest.size(), 5497);
    }
}