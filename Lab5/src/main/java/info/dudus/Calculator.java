package info.dudus;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by f0rest94 on 2017-03-06.
 */
@Path("/calculator")
@Remote(CalculatorRemote.class)
@Stateless
public class Calculator implements CalculatorRemote {

    @GET
    @Path("ping")
    public String ping() {
        return "pong";
    }

    @POST
    @Path("calculate")
    public String getResult(@FormParam("value1") String n1,
                            @FormParam("value2") String n2,
                            @FormParam("value3") String symbol) {

        try {
            return calculate(Double.valueOf(n1), Double.valueOf(n2),
                    symbol).toString();

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Only real numbers are allowed");
        }

    }

    public Double calculate(Double number1, Double number2, String symbol) {

        double result = 0;
        switch (symbol.charAt(0)) {
            case '+':
                return number1 + number2;
            case '-':
                return number1 * number2;
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

}
