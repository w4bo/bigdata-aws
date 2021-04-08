package lambda;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static lambda.Utils.invokeLambda;

public class ReadDataset {
    /**
     * @return the content of the specified dataset
     */
    public static String read() {
        try {
            return new String(Files.readAllBytes(Paths.get("datasets/sales.json")), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Read the dataset and invoke the Preprocess lambda function
     * @param args ignored here
     */
    public static void main(final String[] args) {
        invokeLambda("Preprocess", read());
    }
}
