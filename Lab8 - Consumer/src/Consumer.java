import javax.naming.*;
import javax.jms.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Consumer implements MessageListener {

    private InitialContext context;
    private TopicConnectionFactory connectionFactory;
    private TopicConnection topicConnection;
    private TopicSession subSession;
    private String topicName = "jms/SimpleTopic";
    private Topic topic;
    private TopicSubscriber topicSubscriber;

    public Consumer(int connectionType) {

        try {
            context = new InitialContext();
            connectionFactory = (TopicConnectionFactory) context.lookup("jms/SimpleConnection");
            topic = (Topic) context.lookup(topicName);
            topicConnection = connectionFactory.createTopicConnection();
            subSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            topicSubscriber = subSession.createSubscriber(topic);

            if (connectionType == 1) {
                topicSubscriber.setMessageListener(this);
                topicConnection.start();
            } else if (connectionType == 2)
                onSynchronousMessage();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void onSynchronousMessage() throws JMSException {

        while (true) {
            topicConnection.start();
            System.out.println(((TextMessage) topicSubscriber.receive()).getText());
        }
    }

    public void onMessage(Message msg) {
        TextMessage message = (TextMessage) msg;
        try {
            System.out.println(message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        System.out.println("*****************************");
        System.out.println("********* CONSUMER **********");
        System.out.println("*****************************");
        System.out.println("Choose connection");
        System.out.println("1. Asynchronous");
        System.out.println("2. Synchronous");
    }

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Consumer consumer = new Consumer(Integer.valueOf(br.readLine()));
            consumer.menu();

            if (br.readLine().equalsIgnoreCase("exit")) {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}