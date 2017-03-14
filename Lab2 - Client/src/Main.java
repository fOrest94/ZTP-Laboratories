import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/result");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            String param = "value1=" + args[0] + "&value2=" + args[2] + "&value3=" + args[1];
            byte[] postData = param.getBytes(StandardCharsets.UTF_8);
            con.setRequestProperty("Content-Length", Integer.toString(postData.length));
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            System.out.println(getResponse(con.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getResponse(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuilder out = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null)
            out.append(inputLine);
        bufferedReader.close();

        return out.toString();
    }
}
