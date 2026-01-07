import com.example.sync.MessageQueue;
import com.example.sync.MessageReceiver;
import com.example.sync.MessageSender;

public class Main {
    public static void main(String[] args) {
        MessageQueue sharedQueue = new MessageQueue();

        // Create 3 Senders (Producers)
        for (int i = 1; i <= 3; i++) {
            new Thread(new MessageSender(sharedQueue), "Sender-" + i).start();
        }

        // Create 3 Receivers (Consumers)
        for (int i = 1; i <= 3; i++) {
            new Thread(new MessageReceiver(sharedQueue), "Receiver-" + i).start();
        }
    }
}
