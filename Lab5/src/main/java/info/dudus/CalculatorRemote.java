package info.dudus;

import javax.ejb.Remote;

/**
 * Created by f0rest94 on 2017-05-07.
 */
@Remote
public interface CalculatorRemote {

    Double calculate(Double value1, Double value2, String symbol);
}
