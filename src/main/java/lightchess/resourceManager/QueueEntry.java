package lightchess.resourceManager;

public class QueueEntry {

    private String name;
    private String path;
    private boolean async = false;

    public QueueEntry(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public QueueEntry(String name, String path, boolean async) {
        this.name = name;
        this.path = path;
        this.async = async;
    }

    String getName() {
        return name;
    }

    String getPath() {
        return path;
    }

    boolean isAsync() {
        return async;
    }

}