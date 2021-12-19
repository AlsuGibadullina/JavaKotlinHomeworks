import org.junit.Assert;
import org.junit.Test;

public class CalculatorTests {

    @Test
    public void calculate_sumTest(){
        String result = Calculator.calculate(10, "+", 5);
        Assert.assertEquals(result, "Result is: 15");
    }

    @Test
    public void calculate_differenceTest(){
        String result = Calculator.calculate(10, "-", 5);
        Assert.assertEquals(result, "Result is: 5");
    }

    @Test
    public void calculate_multiplicationTest(){
        String result = Calculator.calculate(10, "*", 5);
        Assert.assertEquals(result, "Result is: 50");
    }

    @Test
    public void calculate_divisionTest(){
        String result = Calculator.calculate(10, "/", 5);
        Assert.assertEquals(result, "Result is: 2");
    }
}
