import Exceptions.NumberOfArgumentsException;
import Exceptions.OperatorException;

public class Loader {
    public static void main(String[] args) {
        try {
            Parser.checkNumberOfArguments(args.length);
        } catch (NumberOfArgumentsException e) {
            e.printStackTrace();
        }
        var val1 = Parser.parseIntOrError(args[0]);
        String operator = null;
        try {
            operator = Parser.parseOperatorOrError(args[1]);
        } catch (OperatorException e) {
            e.printStackTrace();
        }
        var val2 = Parser.parseIntOrError(args[2]);
        System.out.println(Calculator.calculate(val1, operator, val2));
    }

}
