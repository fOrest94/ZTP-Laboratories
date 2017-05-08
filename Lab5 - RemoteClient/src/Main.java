import info.dudus.CalculatorRemote;
import org.apache.openejb.client.RemoteInitialContextFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws NamingException {

        try {
            if(!args[1].equals("+") && !args[1].equals("-") && !args[1].equals("/") && !args[1].equals("*")){
                System.out.println("Nieobsługiwany znak arytmetyczny");
                return;
            }
            try {
                Double v1 = Double.valueOf(args[0]);
                Double v2 = Double.valueOf(args[2]);

                Properties properties = new Properties();
                properties.put("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
                properties.put("java.naming.provider.url", "http://127.0.0.1:8080/tomee/ejb");
                InitialContext initialContext = new InitialContext(properties);

                CalculatorRemote calculator = (CalculatorRemote) initialContext.lookup("global/Calculator!info.dudus.CalculatorRemote");
                System.out.println(calculator.calculate(v1, v2, args[1]));
            }
            catch (NumberFormatException e){
                System.out.println("To nie liczba");
                return;
            }

            if(args[1].equals("+"))
                args[1] = "%2B";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
