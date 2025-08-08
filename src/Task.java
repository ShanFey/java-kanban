public class Task {
    public String taskName;
    public String taskDiscription;
    public int taskId;
    public TaskStatus taskStatus;

    public Task(String taskName, String taskDiscription) {
        this.taskStatus = TaskStatus.NEW;
        this.taskName = taskName;
        this.taskDiscription = taskDiscription;
    }

    public void setStatus(TaskStatus status) {
        this.taskStatus = status;
    }

    @Override
    public String toString() {
        return "Задача{" +
                "название='" + taskName + '\'' +
                ", описаниË='" + taskDiscription + '\'' +
                ", id='" + taskId + '\'' +
                ", статус='" + taskStatus + '\'';
    }

}
