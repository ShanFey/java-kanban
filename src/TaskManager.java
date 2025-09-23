import java.util.ArrayList;

public interface TaskManager {
    void addTask(Task newTask);

    void addEpic(Epic newEpic);

    void addSubTask(SubTask newSubTask);

    Task getTask(int taskID);

    Epic getEpic(int epicID);

    SubTask getSubTask(int subTaskID);

    Integer getID(Task currentTask);

    ArrayList getTasksList();

    ArrayList getEpicsList();

    ArrayList getSubTaskList();

    ArrayList getEpicSubTasksList(Epic currentEpic);

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
