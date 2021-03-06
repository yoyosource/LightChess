package lightchess.resourceManager;

import java.util.ArrayList;
import java.util.List;

public class LoadingQueue {

    private List<QueueEntry> queueEntries = new ArrayList<>();

    public LoadingQueue add(String path, String name) {
        queueEntries.add(new QueueEntry(name, path));
        return this;
    }

    public LoadingQueue add(String path, String name, boolean async) {
        queueEntries.add(new QueueEntry(name, path, async));
        return this;
    }

    boolean hasNext() {
        return !queueEntries.isEmpty();
    }

    QueueEntry next() {
        return queueEntries.remove(0);
    }

}