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

    public static void main(String[] args) {
        invokeLambda("Preprocess", read());
    }
}
