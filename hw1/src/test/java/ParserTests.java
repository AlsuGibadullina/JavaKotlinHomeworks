import Exceptions.NumberOfArgumentsException;
import Exceptions.OperatorException;
import org.junit.Assert;
import org.junit.Test;

public class ParserTests {

    @Test
    public void parseIntOrError_fail(){
        try {
            Parser.parseIntOrError("l");
            Assert.fail();
        }catch (NumberFormatException e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void parseIntOrError_correct(){
        int i = Parser.parseIntOrError("1");
        Assert.assertEquals(1, i);
    }

    @Test
    public void checkNumberOfArguments_fail(){
        try {
            Parser.checkNumberOfArguments(2);
            Assert.fail();
        }catch (NumberOfArgumentsException e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void parseOperatorOrError_fail(){
        try {
            Parser.parseOperatorOrError("l");
            Assert.fail();
        }catch (OperatorException e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void parseOperatorOrError_correct() throws OperatorException {
        String s = Parser.parseOperatorOrError("+");
        Assert.assertSame("+", s);
    }
}