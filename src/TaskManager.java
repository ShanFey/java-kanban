import java.util.List;

public interface TaskManager {
    void addTask(Task newTask);

    void addEpic(Epic newEpic);

    void addSubTask(SubTask newSubTask);

    Task getTask(int taskId);

    Epic getEpic(int epicID);

    SubTask getSubTask(int subTaskId);

    Integer getId(Task currentTask);

    List getTasksList();

    List getEpicsList();

    List getSubTaskList();

    List getEpicSubTasksList(Epic currentEpic);

    void updateTask(Task newVersionTask);

    void updateEpic(Epic newVersionEpic);

    void updateSubTask(SubTask newVersionSubTask);

    void removeTaskById(int taskId);

    void removeEpicById(int epicId);

    void removeSubTaskById(int subTaskId);

    void checkEpicStatus(Epic parentEpic);

    void clearAllTask();

    void clearAllEpic();

    void clearAllSubTask();

    HistoryManager getHistoryManager();

}
