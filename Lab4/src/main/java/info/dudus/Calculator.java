package info.dudus;

import javax.ejb.Local;

/**
 * Created by f0rest94 on 2017-03-12.
 */
@Local
public interface Calculator {

    double getResult(double l1, double l2, char symbol);

    char getSymbol();

    void setSymbol(char symbol);

    double getNumber1();

    void setNumber1(double number1);

    double getNumber2();

    void setNumber2(double number2);
}
