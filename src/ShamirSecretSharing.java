import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.math.BigInteger;
import java.util.*;
import org.json.JSONObject;

public class ShamirSecretSharing {
    public static void main(String[] args) {
        try {
            // Load JSON data for test cases
            JSONObject testCase1 = readJSONFile("testcase1.json");
            JSONObject testCase2 = readJSONFile("testcase2.json");

            // Calculate and display results for each test case
            System.out.println("Test Case 1 Result: " + computeConstantTerm(testCase1));
            System.out.println("Test Case 2 Result: " + computeConstantTerm(testCase2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a JSON file and converts it to a JSONObject.
     */
    public static JSONObject readJSONFile(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONObject(fileContent);
    }

    /**
     * Computes the constant term of the polynomial using Lagrange interpolation.
     */
    public static BigInteger computeConstantTerm(JSONObject testCaseData) {
        // Extract n and k values
        JSONObject parameters = testCaseData.getJSONObject("keys");
        int n = parameters.getInt("n");
        int k = parameters.getInt("k");

        // Validate the input values
        if (n < k) {
            throw new IllegalArgumentException("The number of roots (n) must be greater than or equal to k.");
        }

        // Prepare lists to hold x and y values
        List<BigInteger> xValues = new ArrayList<>();
        List<BigInteger> yValues = new ArrayList<>();

        // Extract and decode points from the JSON object
        for (String pointKey : testCaseData.keySet()) {
            if (pointKey.equals("keys")) continue;

            JSONObject pointData = testCaseData.getJSONObject(pointKey);
            int xValue = Integer.parseInt(pointKey);
            int base = pointData.getInt("base");
            String encodedY = pointData.getString("value");
            BigInteger yValue = new BigInteger(encodedY, base);

            xValues.add(BigInteger.valueOf(xValue));
            yValues.add(yValue);
        }

        // Use Lagrange interpolation to compute the constant term
        return performLagrangeInterpolation(xValues, yValues, k);
    }

    /**
     * Performs Lagrange interpolation to calculate the constant term.
     */
    public static BigInteger performLagrangeInterpolation(List<BigInteger> xValues, List<BigInteger> yValues, int k) {
        BigInteger constantTerm = BigInteger.ZERO;

        // Iterate through each point and apply Lagrange formula
        for (int i = 0; i < k; i++) {
            BigInteger term = yValues.get(i);
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            // Calculate the product of terms for numerator and denominator
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    numerator = numerator.multiply(xValues.get(j).negate());
                    denominator = denominator.multiply(xValues.get(i).subtract(xValues.get(j)));
                }
            }

            // Add the calculated term to the result
            term = term.multiply(numerator).divide(denominator);
            constantTerm = constantTerm.add(term);
        }

        return constantTerm;
    }
}
