package eventLoop.model;


public class EventResult {
    @Override
    public String toString() {
        return "EventResult{" +
                "key='" + key + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    private String key;

    private String result;

    public EventResult(String key, String result) {
        this.key = key;
        this.result = result;
    }

}
