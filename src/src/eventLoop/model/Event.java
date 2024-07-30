package eventLoop.model;

public final class Event {


    private final String key;
    private final String data;
    private final boolean asynchronous;

    public Event(String key, String data, boolean asynchronous) {
        this.key = key;
        this.data = data;
        this.asynchronous = asynchronous;
    }

    public Event(String key, String data) {
        this.key = key;
        this.data = data;
        this.asynchronous = true;
    }

    public String getKey() {
        return key;
    }

    public String getData() {
        return data;
    }

    public boolean isAsynchronous() {
        return asynchronous;
    }
}
