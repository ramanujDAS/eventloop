package eventLoop.service;

import eventLoop.model.Event;
import eventLoop.model.EventResult;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

public class EventLoop {
    private final Deque<Event> eventDeque;
    private final Deque<EventResult> processedEvents;

    private final Map<String, Function<String, String>> handlers;

    public EventLoop() {
        this.handlers = new HashMap<>();
        this.eventDeque = new ArrayDeque<>();
        this.processedEvents = new LinkedList<>();
    }

    public EventLoop on(String key, Function<String, String> handler) {
        handlers.put(key, handler);
        return this;
    }

    public void dispatch(Event event) {
        eventDeque.add(event);
    }

    public void run() {
        Event event = eventDeque.pop();
        if (handlers.containsKey(event.getKey())) {
            Instant startTime = Instant.now();
            if (event.isAsynchronous()) {
                processAsync(event);
            } else {
                processSync(event);
            }

            Instant endTime = Instant.now();
            System.out.println(" eventLoop was blocked for time ::  " + Duration.between(startTime, endTime).toMillis());

        } else {
            System.out.println("no handlers found ");
        }

    }

    private void processSync(Event event) {
        EventResult result = new EventResult(event.getKey(), handlers.get(event.getKey()).apply(event.getData()));
        System.out.printf("output:: " + result);
    }

    private void processAsync(Event event) {
        new Thread(() -> {
            processedEvents.add(new EventResult(event.getKey(), handlers.get(event.getKey()).apply(event.getData())));
        });
    }

}
