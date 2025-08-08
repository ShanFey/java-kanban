public class SubTask extends Task {
    public Epic parentEpic;

    public SubTask(String taskName, String taskDiscription, Epic parentEpic) {
        super(taskName, taskDiscription);
        this.parentEpic = parentEpic;
        this.taskStatus = TaskStatus.NEW;
    }

    @Override
    public String toString() {
        return "Подзадача{" +
                "название='" + taskName + '\'' +
                ", описание='" + taskDiscription + '\'' +
                ", id='" + taskId + '\'' +
                ", статус='" + taskStatus +
                ", Эпик ='" + parentEpic.taskName + '\'';
    }

}
