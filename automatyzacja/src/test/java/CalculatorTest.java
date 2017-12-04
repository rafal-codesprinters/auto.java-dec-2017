import org.junit.Assert;
import org.junit.Test;



/**
 * Created by Rafal on 2017-12-04.
 */
public class CalculatorTest {
    @Test
    public void canAddPositiveIntegers() throws Exception {
        double a = 10.0;
        double b = 5.0;
        Assert.assertTrue("10 + 5 = 15", Calculator.add(a,b) == 15.0);
    }

    @Test
    public void canSubtractPositiveIntegers() throws Exception {
        double a = 10.0;
        double b = 5.0;
        Assert.assertTrue("10 - 5 = 5", Calculator.subtract(a,b) == 5.0);
    }

    @Test
    public void canMultiplyPositiveIntegers() throws Exception {
        double a = 10.0;
        double b = 5.0;
        Assert.assertTrue("10 * 5 = 50", Calculator.multiply(a,b) == 50.0);
    }

    @Test
    public void canDividePositiveIntegers() throws Exception {
        double a = 10.0;
        double b = 5.0;
        Assert.assertTrue("Verify that 10 / 5 = 2", Calculator.divide(a,b) == 2.0);
    }

    @Test
    public void canDividePositiveIntegerByZero() {
        double a = 10.0;
        double b = 0.0;
        Assert.assertTrue("Verify that 10 / 0 = positive infinity", Calculator.divide(a,b) == Double.POSITIVE_INFINITY);
    }

    @Test
    public void canDivideNegativeIntegerByZero() {
        double a = -10.0;
        double b = 0.0;
        Assert.assertTrue("Verify that -10 / 0 = negative infinity", Calculator.divide(a,b) == Double.NEGATIVE_INFINITY);
    }

    @Test
    public void canDivideZeroByZero() {
        double a = 0.0;
        double b = 0.0;
        Assert.assertTrue("Verify that 0 / 0 = 0", Calculator.divide(a,b) == 0.0);
    }
}