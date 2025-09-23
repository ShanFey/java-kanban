import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final int maxSizeHistory = 10;
    private final List<Task> taskHistoryList = new ArrayList<>(maxSizeHistory);
    @Override
    public void add(Task task) {
        if (taskHistoryList.size() == maxSizeHistory) {
            taskHistoryList.remove(0);
        }
        taskHistoryList.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return taskHistoryList;
    }
}
