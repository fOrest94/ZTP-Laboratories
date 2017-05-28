import javax.jms.*;
import javax.naming.*;
import java.io.*;

public class Producer {
    private TopicConnectionFactory connectionFactory;
    private TopicConnection topicConnection;
    private TopicSession topicSession;
    private String topicName = "jms/SimpleTopic";
    private Topic topic;
    private TopicPublisher topicPublisher;

    public Producer() {

        try {
            InitialContext ctx = new InitialContext();
            connectionFactory = (TopicConnectionFactory) ctx.lookup("jms/SimpleConnection");
            topic = (Topic) ctx.lookup(topicName);
            topicConnection = connectionFactory.createTopicConnection();
            topicSession = topicConnection.createTopicSession
                    (false, Session.AUTO_ACKNOWLEDGE);
            topicPublisher = topicSession.createPublisher(topic);
            topicConnection.start();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void close() throws JMSException {
        topicConnection.stop();
    }

    public void writeMsg(String msg) throws JMSException {

        TextMessage txtMsg = topicSession.createTextMessage(msg);
        topicPublisher.publish(txtMsg);
    }

    public void menu() {
        System.out.println("*****************************");
        System.out.println("********* PRODUCER **********");
        System.out.println("*****************************");
    }

    public static void main(String[] args) {

        Producer producer = new Producer();
        producer.menu();
        String message;
        System.out.println("Write sth to consumer");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                message = br.readLine();
                if (message.equalsIgnoreCase("exit")) {
                    producer.writeMsg(message);
                    producer.close();
                    System.exit(0);
                } else
                    producer.writeMsg(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}