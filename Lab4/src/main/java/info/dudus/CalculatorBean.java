package info.dudus;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by f0rest94 on 2017-03-06.
 */
@Stateless
public class CalculatorBean implements Calculator{

    private char symbol;
    private double number1;
    private double number2;

    public double getResult(double l1, double l2, char symbol) {

        double result = 0;
        switch (symbol) {
            case '+':
                return number1 + number2;
            case '-':
                return number1 - number2;
            case '*':
                return number1 * number2;
            case '/': {
                if (number2 == 0)
                    throw new IllegalArgumentException("you can't divide with 0");
                return number1 / number2;
            }
        }
        return result;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }
}
