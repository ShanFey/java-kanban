import java.util.HashMap;

public class Epic extends Task {
    public HashMap<Integer,SubTask> subTaskMap;
    public Epic(String taskName, String taskDiscription) {
        super(taskName, taskDiscription);
        this.subTaskMap = new HashMap<>();
        this.taskStatus = TaskStatus.NEW;
    }

    @Override
    public String toString() {
        if (subTaskMap.isEmpty()) {
            return "Эпик{" +
                    "название='" + taskName + '\'' +
                    ", описание='" + taskDiscription + '\'' +
                    ", id='" + taskId + '\'' +
                    ", статус='" + taskStatus + '}' + '\'' +
                    ", подзадач - нет";
        } else {
            return "Эпик{" +
                    "название='" + taskName + '\'' +
                    ", описание='" + taskDiscription + '\'' +
                    ", id='" + taskId + '\'' +
                    ", статус='" + taskStatus + '\'' +
                    ", подзадача(и)='" + subTaskMap.values().toString() + '}' + '\'';
        }
    }

}
