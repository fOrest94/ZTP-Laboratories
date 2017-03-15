import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client extends JFrame {

    private static final String URL_ADDRESS = "http://localhost:8080/picture";

    public static void main(String[] args) {

        try {
            Client client = new Client();
            client.showChoices();
            HttpURLConnection con = client.sendRequest();
            client.showPicture(IOUtils.readFully(con.getInputStream(),
                    con.getContentLength(), true));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChoices() {
        System.out.println("Homie, what continent you wanna see?");
        System.out.println("1. Europa\n2. North America\n3. South America");
        System.out.println("4. Asia\n5. Australia\n6. Africa\n7. Antarctica");
    }

    public HttpURLConnection sendRequest() throws IOException {

        URL url = new URL(URL_ADDRESS);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        String param = "picture=" + Integer.valueOf(new Scanner(System.in).nextLine());
        byte[] postData = param.getBytes(StandardCharsets.UTF_8);
        con.setRequestProperty("Content-Length", Integer.toString(postData.length));
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write(postData);
        }
        return con;
    }

    public void showPicture(byte[] sourcePicture) throws IOException {
        InputStream in = new ByteArrayInputStream(sourcePicture);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        int height = new ImageIcon(bImageFromConvert).getIconHeight();
        int width = new ImageIcon(bImageFromConvert).getIconWidth();
        JLabel picLabel = new JLabel(new ImageIcon(bImageFromConvert));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((dim.width - width) / 2, (dim.height - height) / 2, width, height);
        add(picLabel);
        setVisible(true);

    }


}
