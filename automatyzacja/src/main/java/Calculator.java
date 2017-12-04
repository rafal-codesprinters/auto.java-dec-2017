/**
 * Created by Rafal on 2017-12-04.
 */
public class Calculator {
    static double add(double a, double b) {
        return a + b;
    }

    static double subtract(double a, double b) {
        return a - b;
    }

    static double multiply(double a, double b) {
        return a * b;
    }

    static double divide(double a, double b) {
        if (a == 0 && b == 0) {
            return 0.0;
        } else {
            return a / b;
        }
    }
}
