public class Calculator {
    public static String calculate(int val1, String operator, int val2) {
        var result = switch (operator) {
            case "-" -> val1 - val2;
            case "+" -> val1 + val2;
            case "/" -> val1 / val2;
            case "*" -> val1 * val2;
            default -> 0;
        };

        return "Result is: " + result;
    }
}
