import Exceptions.NumberOfArgumentsException;
import Exceptions.OperatorException;

public class Parser {
    public static String parseOperatorOrError(String arg) throws OperatorException {
        if (!"-".equals(arg) && !"+".equals(arg) && !"/".equals(arg) && !"*".equals(arg)){
            throw new OperatorException("Operator is not supported");
        } else {
            return arg;
        }
    }

    public static int parseIntOrError(String arg) throws NumberFormatException{
        try {
            Integer.parseInt(arg);
            return Integer.parseInt(arg);
        }catch (NumberFormatException e){
            System.out.println(arg + " is not int");
            throw e;
        }
    }

    public static void checkNumberOfArguments(int length) throws NumberOfArgumentsException {
        if (length != 3){
            throw new NumberOfArgumentsException("Ars format is: var1 +|-|/|* var2");
        }
    }
}