package eventLoop;

import eventLoop.model.Event;
import eventLoop.service.EventLoop;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        EventLoop eventLoop = new EventLoop();

        Random random = new Random();
        do {
            for (int i = 0; i < 1000; i++) {
                String id = "" + random.nextLong();
                eventLoop.on(id, data -> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            return String.format(data);
                        })
                        .dispatch(new Event(id, "how are you :: " + Thread.currentThread().getName()));
            }
            eventLoop.run();
        }
        while (true);


    }

    public static String function(String data) throws InterruptedException {
        Thread.sleep(1000);
        return String.format(data);
    }

}
