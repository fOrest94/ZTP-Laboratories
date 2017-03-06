package info.dudus;

/**
 * Created by f0rest94 on 2017-03-06.
 */
public class Calculator {

    public double getResult(double l1, double l2, String method) {

        double result = 0;
        switch (method.charAt(0)) {
            case '+':
                result = addition(l1, l2);
                break;
            case '-':
                result = subtraction(l1, l2);
                break;
            case '*':
                result = multiplication(l1, l2);
                break;
            case '/':
                result = division(l1, l2);
                break;
        }
        return result;
    }

    public double addition(double a, double b) {
        return a + b;
    }

    public double subtraction(double a, double b) {
        return a - b;
    }

    public double multiplication(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        if (b == 0)
            throw new IllegalArgumentException("you can't divide with 0");
        return a / b;
    }


}
